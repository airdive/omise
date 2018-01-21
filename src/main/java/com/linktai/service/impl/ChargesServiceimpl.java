package com.linktai.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.google.zxing.WriterException;
import com.linktai.dao.ChargesMapper;
import com.linktai.dao.TicketMapper;
import com.linktai.pojo.CardOfAc;
import com.linktai.pojo.Charges;
import com.linktai.pojo.Mail;
import com.linktai.pojo.Ticket;
import com.linktai.service.IChargesService;
import com.linktai.utils.Graphies;
import com.linktai.utils.MailUtils;
import com.linktai.utils.PageUtil;
import com.linktai.utils.rsa.RsaUtils;
import com.linktai.utils.zxing.ZxingUtils;

import co.omise.Client;
import co.omise.ClientException;
import co.omise.models.Card;
import co.omise.models.Charge;
import co.omise.models.OmiseException;
import co.omise.models.Token;
import co.omise.models.Token.Create;

@Service
public class ChargesServiceimpl implements IChargesService {
	@Autowired
	private StringRedisTemplate redisTemplate;
	@Autowired
	private ChargesMapper chargesMapper;
	@Autowired
	private TicketMapper ticketMapper;

	public PageUtil<Charges> listPage(Integer cp, Integer ps, String select) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		if (select != null && select != "") {
			select = select + "*";
		} else {
			select = null;
		}
		hashMap.put("select", select);
		Integer allCount = chargesMapper.findAllCount(hashMap);
		System.out.println(allCount);
		Integer allPage = (allCount + ps - 1) / ps;
		Integer start = cp * ps;
		hashMap.put("start", start);
		hashMap.put("ps", ps);
		List<Charges> page = chargesMapper.findByPage(hashMap);
		PageUtil<Charges> pageUtil = new PageUtil<Charges>(page, cp, ps, allPage);
		return pageUtil;
	}

	public Map<String, String> charges(String arg, CardOfAc card1) {
		System.out.println(card1);

		HashMap<String, String> hashMap = new HashMap<String, String>();
		

		if (arg == null || card1 == null) {
			hashMap.put("state", "1");
			return hashMap;
		}
		String chargesJson = (String) redisTemplate.opsForHash().get("account", arg);
		JSON parse = (JSON) JSON.parse(chargesJson);
		Charges charges = JSON.toJavaObject(parse, Charges.class);
		
		if (charges == null) {
			hashMap.put("charges", "kkkkk");
			return hashMap;
		}
		
		Charge charge = null;
		try {
			Client client = new Client(PUBLIC_KEY, PRIVATE_KEY);
			Create card = new Token.Create().card(new Card.Create().name(card1.getNameOnCard())
					.number(card1.getCardNumber()).expiration(card1.getExpiryMonth(), card1.getExpiryYear())
					.securityCode(card1.getSecurityCode()));
			Token token = client.tokens().create(card);

			charge = client.charges().create(new Charge.Create().amount(100000) // THB 1,000.00
					.currency("sgd").card(token.getId()));
			if (charge == null) {
				hashMap.put("state", "kkkkkkkk");
				return hashMap;
			}
			Integer payTicket = ticketMapper.payTicket();
			final String chargeid = charge.getId();

			// ���׳ɹ��������������ݿ�,���׼�¼
			Ticket ticket = ticketMapper.findTicket();
			charges.setTicketId(ticket.getId());
			charges.setChargesRental(ticket.getPrice());
			charges.setChargesTime(new Date());
			charges.setChargesState(0);
			charges.setIssendmail(0);
			charges.setChargesNumberOmise(charge.getId());
			charges.setIsused(0);
			charges.setCardnumber(card1.getCardNumber());
			int insert = chargesMapper.insert(charges);
			
			hashMap.put("charges", charges.toString());
			MyThread myThread = new MyThread(charges, card1);
			myThread.start();
			
			
			
			hashMap.put("state", "0");
			return hashMap;
		} catch (ClientException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (OmiseException e) {
			e.printStackTrace();
		}
		hashMap.put("state", "1");
		hashMap.put("failMessage", charge.getFailureMessage());
		return hashMap;
	}

	class MyThread extends Thread {
		private Charges charges;
		private CardOfAc card1;

		public MyThread() {
			// TODO Auto-generated constructor stub
		}

		public MyThread(Charges charges, CardOfAc card1) {
			super();
			this.charges = charges;
			this.card1 = card1;
		}

		public Charges getCharges() {
			return charges;
		}

		public void setCharges(Charges charges) {
			this.charges = charges;
		}

		public CardOfAc getCard1() {
			return card1;
		}

		public void setCard1(CardOfAc card1) {
			this.card1 = card1;
		}

		@Override
		public void run() {
			// ���ý��ױ������ǩ��
			String sign = RsaUtils.createSign(charges.getChargesNumberOmise());

			try {
				// ���ɶ�ά��,���ض�ά��·��
				String path = ZxingUtils.Encode_QR_CODE(sign);
				String file = Graphies.creatFile(path, "images/ticket.png", "" + charges.getChargesId(),
						charges.getName());
				Mail mail = new Mail(charges.getEmail(), null, null, new Date(), new File(file));

				
				// �����ʼ�
				boolean sendMessage = MailUtils.sendMessage(mail,charges.getName(),charges.getLang());
				// �޸�charges��Ϣ
				charges.setZxingcodepath(file);
				charges.setSign(sign);
				int updateCharges = chargesMapper.updatePathAndSign(charges);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriterException e) {
				e.printStackTrace();
			}

		}
	}

	public Map<String, Integer> charges(Charges charges) {
		charges.setChargesRental(0);
		// 自己生成OmiseNumber用于验证
		String substring = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
		charges.setChargesNumberOmise("linkTime_" + substring);
		int chargesid = chargesMapper.insert(charges);
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		hashMap.put("chargesid", chargesid);
		MyThread myThread = new MyThread();
		myThread.setCharges(charges);
		myThread.start();
		return hashMap;
	}

	public Map<String, String> updateChargesInfo(Charges charges) {
		Integer info = chargesMapper.updateChargesInfo(charges);
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("state", "0");
		return hashMap;
	}

	public Charges refound(Integer chargesId) {
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		Integer put = hashMap.put("chargesId", chargesId);
		Charges charges = chargesMapper.findChargesByChargesId(hashMap);

		return charges;
	}

	public boolean delete(Integer chargesId) {

		return false;
	}

}
