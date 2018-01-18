package com.linktai.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public Map<String, String> charges(Charges charges, CardOfAc card1) {
		if (charges == null || card1 == null) {
			return null;
		}
		HashMap<String, String> hashMap = new HashMap<String, String>();
		Charge charge = null;
		try {
			Client client = new Client(PUBLIC_KEY, PRIVATE_KEY);
			// ���п���Ϣ
			Create card = new Token.Create().card(new Card.Create().name(card1.getNameOnCard())
					.number(card1.getCardNumber()).expiration(card1.getExpiryMonth(), card1.getExpiryYear())
					.securityCode(card1.getSecurityCode()));
			Token token = client.tokens().create(card);
			// �¼��±�
			// ����һ������
			charge = client.charges().create(new Charge.Create().amount(100000) // THB 1,000.00
					.currency("sgd").card(token.getId()));

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
			int insert = chargesMapper.insert(charges);
			// �ʼ�����
			MyThread myThread = new MyThread(charges, card1);
			myThread.start();
			// ǰ�˷���״̬
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
				String file = Graphies.creatFile(path, "D:/tickets.png", "8001", charges.getName());
				Mail mail = new Mail(charges.getEmail(), "你好", "购买成功---------------->", new Date(), new File(file));
				// �����ʼ�
				boolean sendMessage = MailUtils.sendMessage(mail);
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
		int chargesid = chargesMapper.insert(charges);
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		hashMap.put("chargesid", chargesid);
		return hashMap;
	}
	

}
