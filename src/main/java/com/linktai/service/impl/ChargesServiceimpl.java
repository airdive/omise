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
import com.linktai.dao.FailInfoMapper;
import com.linktai.dao.TicketMapper;
import com.linktai.pojo.CardOfAc;
import com.linktai.pojo.Charges;
import com.linktai.pojo.FailInfo;
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
	@Autowired
	private FailInfoMapper failInfoMapper;

	public PageUtil<Charges> listPage(Integer cp, Integer ps, String select) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		if (select == null || select.equals("")) {
			select = null;
		} else {
			select = select + "*";
		}
		hashMap.put("select", select);
		Integer allCount = chargesMapper.findAllCount(hashMap);
		System.out.println("allcount"+allCount);
		Integer allPage = (allCount + ps - 1) / ps;
		Integer start = cp * ps;
		hashMap.put("start", start);
		hashMap.put("ps", ps);
		List<Charges> page = chargesMapper.findByPage(hashMap);
		PageUtil<Charges> pageUtil = new PageUtil<Charges>(page, cp, ps, allPage);
		return pageUtil;
	}

	public Map<String, String> charges(String arg, CardOfAc card1) {

		HashMap<String, String> hashMap = new HashMap<String, String>();

		if (arg == null || card1 == null) {
			hashMap.put("state", "1");
			return hashMap;
		}
		String chargesJson = (String) redisTemplate.opsForHash().get("account", arg);
		JSON parse = (JSON) JSON.parse(chargesJson);
		Charges charges = JSON.toJavaObject(parse, Charges.class);
		/**
		 * 清除数据，避免表单重复提交
		 */
		redisTemplate.opsForHash().delete("account", arg);
		if (charges == null) {
			// 过期
			hashMap.put("state", "3");
			return hashMap;
		}

		Charge charge = null;
		try {
			Client client = new Client(PUBLIC_KEY, PRIVATE_KEY);
			Create card = new Token.Create().card(new Card.Create().name(card1.getNameOnCard())
					.number(card1.getCardNumber()).expiration(card1.getExpiryMonth(), card1.getExpiryYear())
					.securityCode(card1.getSecurityCode()));
			Token token = client.tokens().create(card);

			charge = client.charges().create(new Charge.Create().amount(3500).currency("usd").card(token.getId()));
			if (!charge.getStatus().toString().equals("Successful")) {
				// 交易失败
				hashMap.put("state", "1");
				hashMap.put("failureMessage", charge.getFailureMessage() == null ? null : charge.getFailureMessage());
				FailInfo failInfo = new FailInfo(null, charges.getName(), charges.getCountry(), charges.getCompany(),
						charges.getPosition(), charges.getEmail(), charges.getTelephone(),
						card1.getCardNumber().substring(
								card1.getCardNumber().length() > 4 ? card1.getCardNumber().length() - 4 : 0,
								card1.getCardNumber().length()),
						charge.getFailureMessage(), new Date(), charge.getFailureCode());
				int insert = failInfoMapper.insert(failInfo);
				return hashMap;
			}

			MyThread myThread = new MyThread(charges, card1, charge);
			myThread.start();

			hashMap.put("state", "0");
			hashMap.put("lang", charges.getLang());
			return hashMap;
		} catch (ClientException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (OmiseException e) {
			e.printStackTrace();
		}
		FailInfo failInfo = new FailInfo(null, charges.getName(), charges.getCountry(), charges.getCompany(),
				charges.getPosition(), charges.getEmail(), charges.getTelephone(),
				card1.getCardNumber().substring(
						card1.getCardNumber().length() > 4 ? card1.getCardNumber().length() - 4 : 0,
						card1.getCardNumber().length()),
				"失效信息，名称无效，号码无效或不支持品牌", new Date(), charge == null ? "失效信息，名称无效，号码无效或不支持品牌" : charge.getFailureCode());
		int insert = failInfoMapper.insert(failInfo);
		// 输入信息错误
		hashMap.put("state", "1");
		hashMap.put("failureMessage", "name is invalid or number is invalid or brand not supported ");
		return hashMap;
	}

	class MyThread extends Thread {
		private Charges charges;
		private CardOfAc card1;
		private Charge charge;

		public MyThread() {
			// TODO Auto-generated constructor stub
		}

		public MyThread(Charges charges, CardOfAc card1, Charge charge) {
			super();
			this.charges = charges;
			this.card1 = card1;
			this.charge = charge;
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

		public Charge getCharge() {
			return charge;
		}

		public void setCharge(Charge charge) {
			this.charge = charge;
		}

		@Override
		public void run() {

			Integer payTicket = ticketMapper.payTicket();

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

			// 创建签名
			String sign = RsaUtils.createSign(charges.getChargesNumberOmise());
			sign = charges.getChargesId()+","+sign;

			try {
				// 创建二维码
				String path = ZxingUtils.Encode_QR_CODE(sign);
				// 创建门票
				String file = Graphies.creatFile(path, charges.getLang(), "" + charges.getChargesId(),
						charges.getName());
				Mail mail = new Mail(charges.getEmail(), null, null, new Date(), new File(file));

				// 发送邮件
				MailUtils mailUtils = new MailUtils();
				mailUtils.setSubjectEn("Payment Confirmation and E-ticket for Asia-Pacific Ethereum Community Meetup");
				mailUtils.setContentEn("Hi, \r\n" + 
						"\r\n" + 
						"Thanks for your support to Asia-Pacific Ethereum Community Meetup!\r\n" + 
						"We have received your payment, and attached is your personal E-ticket. Please note that the E-ticket is very important because with that you can get your personal name badge as the pass during the event. Each ticket can only be used once by the holder himself/herself, it shall not be sent, altered or transfered. \r\n" + 
						"With any questions, please email at: meetup@linktimetech.com\r\n" + 
						"Updates will be announced in the following channels, and Don't forget to join our communities! :)\r\n" + 
						"\r\n" + 
						"Website: https://baoming.in/\r\n" + 
						"Telegram: https://t.me/AsiaETHfans\r\n" + 
						"\r\n" + 
						"Again, thank you for your participation. Have a nice day!\r\n" + 
						"\r\n" + 
						"Best regards\r\n" + 
						"LinkTime\r\n" + 
						"\r\n" + 
						"");
				boolean sendMessage = mailUtils.sendMessage(mail, charges.getName(), charges.getLang());
				if(!sendMessage) {
					return;
				}
				// 设置图片路径存入数据库
				charges.setZxingcodepath(file);
				charges.setSign(sign);
				charges.setIssendmail(1);
				// 修改图片路径以及邮件是否已发送状态
				int updateCharges = chargesMapper.updatePathAndSign(charges);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriterException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * 赠票线程
	 * 
	 * @author 17770
	 *
	 */
	class MyThread2 extends Thread {
		private Charges charges;

		public Charges getCharges() {
			return charges;
		}

		public void setCharges(Charges charges) {
			this.charges = charges;
		}

		@Override
		public void run() {

			Ticket ticket = ticketMapper.findTicket();

			charges.setTicketId(ticket.getId());
			charges.setChargesRental(0);
			charges.setChargesTime(new Date());
			charges.setChargesState(0);
			charges.setIssendmail(0);
			charges.setIsused(0);
			int insert = chargesMapper.insert(charges);

			// 创建签名
			String sign = RsaUtils.createSign(charges.getChargesNumberOmise());

			try {
				// 创建二维码
				String path = ZxingUtils.Encode_QR_CODE(sign);
				// 创建门票
				String file = Graphies.creatFile(path, charges.getLang(), "" + charges.getChargesId(),
						charges.getName());
				Mail mail = new Mail(charges.getEmail(), null, null, new Date(), new File(file));

				// 发送邮件
				MailUtils mailUtils = new MailUtils();
				mailUtils.setSubjectEn("Free pass ticket to Asia-Pacific Ethereum Community Meetup");
				mailUtils.setContentEn("Hi, \r\n" + "\r\n"
						+ "Thanks for your support to Asia-Pacific Ethereum Community Meetup!\r\n"
						+ "Attached is your personal E-ticket. Please note that the E-ticket is very important because with that you can get your personal name badge as the pass during the event. \r\n"
						+ "With any questions, please email at: meetup@linktimetech.com\r\n"
						+ "Updates will be announced in the following channels, and Don't forget to join our communities! :)\r\n"
						+ "\r\n" + "Website: https://baoming.in/\r\n" + "Telegram: https://t.me/AsiaETHfans\r\n"
						+ "\r\n" + "Again, thank you for your participation. Have a nice day!\r\n" + "\r\n"
						+ "Best regards\r\n" + "LinkTime\r\n" + "");
				boolean sendMessage = mailUtils.sendMessage(mail, charges.getName(), charges.getLang());
				if(!sendMessage) {
					return;
				}
				// 设置图片路径存入数据库
				charges.setZxingcodepath(file);
				charges.setSign(sign);
				charges.setIssendmail(1);
				// 修改图片路径以及邮件是否已发送状态
				int updateCharges = chargesMapper.updatePathAndSign(charges);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriterException e) {
				e.printStackTrace();
			}
		}
	}

	public Map<String, Integer> charges(Charges charges) {
		// 自己生成OmiseNumber用于验证
		String substring = UUID.randomUUID().toString().replaceAll("-", "");
		charges.setChargesNumberOmise("linkTime_" + substring);
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();

		Integer payTicket = ticketMapper.payTicket();
		Ticket ticket = ticketMapper.findTicket();

		charges.setTicketId(ticket.getId());
		charges.setChargesRental(charges.getChargesRental()==null?0:charges.getChargesRental());
		charges.setChargesTime(new Date());
		charges.setChargesState(0);
		charges.setIssendmail(0);
		charges.setIsused(0);
		int chargesid = chargesMapper.insert(charges);
		hashMap.put("chargesid", charges.getChargesId());
		// MyThread myThread = new MyThread();
		// myThread.setCharges(charges);
		// myThread.start();
		return hashMap;
	}

	public Map<String, String> updateChargesInfo(Charges charges) {
		Integer info = chargesMapper.updateChargesInfo(charges);
		HashMap<String, String> hashMap = new HashMap<String, String>();
		if (info <= 0) {
			hashMap.put("state", "1");
		} else {
			hashMap.put("state", "0");
		}
		return hashMap;
	}

	public Charges refound(Integer chargesId) {
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		Integer put = hashMap.put("chargesId", chargesId);
		Charges charges = chargesMapper.findChargesByChargesId(hashMap);

		return charges;
	}

	public boolean delete(Integer chargesId) {
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		hashMap.put("chargesId", chargesId);
		Integer deleteAccount = chargesMapper.deleteAccount(hashMap);
		if (deleteAccount <= 0) {
			return false;
		}
		return true;
	}

	public PageUtil<Charges> listPageZP(Integer cp, Integer ps, String select) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		if (select == null || select.equals("")) {
			select = null;
		} else {
			select = select + "*";
		}
		hashMap.put("select", select);
		Integer allCount = chargesMapper.findAllCountZP(hashMap);
		Integer allPage = (allCount + ps - 1) / ps;
		Integer start = cp * ps;
		hashMap.put("start", start);
		hashMap.put("ps", ps);
		List<Charges> page = chargesMapper.findByZP(hashMap);
		PageUtil<Charges> pageUtil = new PageUtil<Charges>(page, cp, ps, allPage);
		return pageUtil;
	}

	/**
	 * 发送邮件功能
	 */
	public Map<String, String> sendMail(Integer chargesId) {
		HashMap<String,String> map = new HashMap<String, String>();
		
		HashMap<String,Integer> hashMap = new HashMap<String, Integer>();
		hashMap.put("chargesId", chargesId);
		Charges charges = chargesMapper.findChargesByChargesId(hashMap);
		// 创建签名
		String sign = RsaUtils.createSign(charges.getChargesNumberOmise());

		try {
			// 创建二维码
			String path = ZxingUtils.Encode_QR_CODE(sign);
			// 创建门票
			String file = Graphies.creatFile(path, charges.getLang(), "" + charges.getChargesId(), charges.getName());
			Mail mail = new Mail(charges.getEmail(), null, null, new Date(), new File(file));

			// 发送邮件
			MailUtils mailUtils = new MailUtils();
			boolean sendMessage = mailUtils.sendMessage(mail, charges.getName(), charges.getLang());
			// 设置图片路径存入数据库
			charges.setZxingcodepath(file);
			charges.setSign(sign);
			charges.setIssendmail(1);
			// 修改图片路径以及邮件是否已发送状态
			int updateCharges = chargesMapper.updatePathAndSign(charges);
			map.put("state", "0");
			return map;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriterException e) {
			e.printStackTrace();
		}
		map.put("state", "1");
		return map;
	}

	public Map<String, String> sendMailZP(Integer chargesId,String lang) {
		HashMap<String, String> map = new HashMap<String, String>();
		if(lang==null) {
			map.put("state", "1");
			return map;
		}
		
		HashMap<String,Integer> hashMap = new HashMap<String, Integer>();
		hashMap.put("chargesId", chargesId);
		Charges charges = chargesMapper.findChargesByChargesId(hashMap);
		// 创建签名
		String sign = RsaUtils.createSign(charges.getChargesNumberOmise());
		//加了编号
		sign = chargesId+","+sign;

		try {
			// 创建二维码
			String path = ZxingUtils.Encode_QR_CODE(sign);
			// 创建门票
			String file = Graphies.creatFile(path, lang, "" + charges.getChargesId(), charges.getName());
			Mail mail = new Mail(charges.getEmail(), null, null, new Date(), new File(file));
			//发交易邮件
			if(charges.getChargesRental()!=null && charges.getChargesRental()>0) {
				System.out.println("交易额"+charges.getChargesRental());
				MailUtils mailUtils = new MailUtils();
				mailUtils.setSubjectEn("Payment Confirmation and E-ticket for Asia-Pacific Ethereum Community Meetup");
				mailUtils.setContentEn("Hi, \r\n" + 
						"\r\n" + 
						"Thanks for your support to Asia-Pacific Ethereum Community Meetup!\r\n" + 
						"We have received your payment, and attached is your personal E-ticket. Please note that the E-ticket is very important because with that you can get your personal name badge as the pass during the event. Each ticket can only be used once by the holder himself/herself, it shall not be sent, altered or transfered. \r\n" + 
						"With any questions, please email at: meetup@linktimetech.com\r\n" + 
						"Updates will be announced in the following channels, and Don't forget to join our communities! :)\r\n" + 
						"\r\n" + 
						"Website: https://baoming.in/\r\n" + 
						"Telegram: https://t.me/AsiaETHfans\r\n" + 
						"\r\n" + 
						"Again, thank you for your participation. Have a nice day!\r\n" + 
						"\r\n" + 
						"Best regards\r\n" + 
						"LinkTime\r\n" + 
						"\r\n" + 
						"");
				boolean sendMessage = mailUtils.sendMessage(mail, charges.getName(), lang);
				if(!sendMessage) {
					map.put("state", "1");
					return map;
				}
				// 设置图片路径存入数据库
				charges.setZxingcodepath(file);
				charges.setSign(sign);
				charges.setIssendmail(1);
				// 修改图片路径以及邮件是否已发送状态
				int updateCharges = chargesMapper.updatePathAndSign(charges);
				map.put("state", "0");
				return map;
				//发送赠票邮件
			}else {
				// 发送邮件
				MailUtils mailUtils = new MailUtils();
				mailUtils.setSubjectEn("Free pass ticket to Asia-Pacific Ethereum Community Meetup");
				mailUtils.setContentEn("Hi, \r\n" + "\r\n"
						+ "Thanks for your support to Asia-Pacific Ethereum Community Meetup!\r\n"
						+ "Attached is your personal E-ticket. Please note that the E-ticket is very important because with that you can get your personal name badge as the pass during the event. \r\n"
						+ "With any questions, please email at: meetup@linktimetech.com\r\n"
						+ "Updates will be announced in the following channels, and Don't forget to join our communities! :)\r\n"
						+ "\r\n" + "Website: https://baoming.in/\r\n" + "Telegram: https://t.me/AsiaETHfans\r\n" + "\r\n"
						+ "Again, thank you for your participation. Have a nice day!\r\n" + "\r\n" + "Best regards\r\n" 
						+ "LinkTime\r\n" + "\r\n" + "");
				boolean sendMessage = mailUtils.sendMessage(mail, charges.getName(), lang);
				if(!sendMessage) {
					map.put("state", "1");
					return map;
				}
				// 设置图片路径存入数据库
				charges.setZxingcodepath(file);
				charges.setSign(sign);
				charges.setIssendmail(1);
				// 修改图片路径以及邮件是否已发送状态
				int updateCharges = chargesMapper.updatePathAndSign(charges);
				map.put("state", "0");
				return map;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriterException e) {
			e.printStackTrace();
		}
		map.put("state", "1");
		return map;
	}
	
	
	public Map<String, Integer> findAllCount(){
		HashMap<String,Object> hashMap = new HashMap<String, Object>();
		hashMap.put("select", null);
		Integer allCount = chargesMapper.findAllCountZP(hashMap);
		Integer allCount2 = chargesMapper.findAllCount(hashMap);
		Integer all = allCount+allCount2;
		HashMap<String,Integer> map = new HashMap<String, Integer>();
		map.put("pp", allCount2);
		map.put("zp", allCount);
		map.put("all", all);
		return map;
	}

}
