package com.linktai.service.impl;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.linktai.dao.ChargesMapper;
import com.linktai.dao.FailInfoMapper;
import com.linktai.dao.MailInfoMapper;
import com.linktai.dao.ManagersMapper;
import com.linktai.pojo.Charges;
import com.linktai.pojo.FailInfo;
import com.linktai.pojo.Mail;
import com.linktai.pojo.MailInfo;
import com.linktai.pojo.Managers;
import com.linktai.service.IChargesService;
import com.linktai.service.ManagerService;
import com.linktai.service.impl.ManagerServerimpl.SendAll;
import com.linktai.utils.Graphies;
import com.linktai.utils.MailUtils;
import com.linktai.utils.PageUtil;
import com.linktai.utils.rsa.RsaUtils;

@Service
public class ManagerServerimpl implements ManagerService {
	@Autowired
	private ManagersMapper managersMapper;
	@Autowired
	private ChargesMapper chargesMapper;
	@Autowired
	private MailInfoMapper mailInfoMapper;
	@Autowired
	private FailInfoMapper failInfoMapper;
	@Autowired
	private IChargesService chargesService;

	public Managers login(Managers managers) {
		if (managers != null) {
			Managers login = managersMapper.login(managers);
			return login;
		}
		return null;
	}

	public boolean updatePWD(Managers managers) {
		Integer updatePWD = managersMapper.updatePWD(managers);
		return updatePWD > 0 ? true : false;
	}

	public PageUtil<Charges> pageListQD(Integer cp, Integer ps, String select) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		if (select == null || select.equals("")) {
			select = null;
		} else {
			select = select + "*";
		}
		hashMap.put("select", select);
		Integer allCount = chargesMapper.findAllCountQD(hashMap);
		Integer allPage = (allCount + ps - 1) / ps;
		Integer start = cp * ps;
		hashMap.put("start", start);
		hashMap.put("ps", ps);
		List<Charges> list = chargesMapper.findByQD(hashMap);
		PageUtil<Charges> pageUtil = new PageUtil<Charges>(list, cp, ps, allPage);
		return pageUtil;
	}

	public Map<String, Integer> accountQD() {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("select", null);
		Integer allCount = chargesMapper.findAllCount(hashMap);
		Integer countZP = chargesMapper.findAllCountZP(hashMap);
		Integer all = allCount + countZP;
		Integer countQD = chargesMapper.findAllCountQD(hashMap);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("allCount", all);
		map.put("countQD", countQD);
		return map;
	}

	public Map<String, String> updateMailContent(MailInfo mailInfo) {
		Integer integer = mailInfoMapper.updateMailContent(mailInfo);
		HashMap<String, String> hashMap = new HashMap<String, String>();
		if (integer > 0) {
			hashMap.put("state", "0");
			return hashMap;
		}
		hashMap.put("state", "1");
		return hashMap;
	}

	public Map<String, Integer> countNotRead() {
		Integer notRead = failInfoMapper.findCountNotRead();
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		hashMap.put("notReadCount", notRead);
		return hashMap;
	}

	public Map<String, String> updateReadState() {
		Integer state = failInfoMapper.updateIsReadState();
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("state", "0");
		return hashMap;
	}

	public Map<String, String> addFailInfoToCharges(Integer id, String name, String email) {
		FailInfo failInfo = failInfoMapper.findFailByID(id);
		Charges charges = new Charges(failInfo.getName(), failInfo.getCountry(), failInfo.getCompany(),
				failInfo.getPosition(), failInfo.getEmail(), failInfo.getTelephine(), failInfo.getLang());
		charges.setChargesRental(3500);
		charges.setIsPayCoin(failInfo.getIsPayCoin());
		charges.setTxhash(failInfo.getTxhash());
		Map<String, Integer> map = chargesService.charges(charges);
		HashMap<String, String> hashMap = new HashMap<String, String>();
		if (map != null && map.get("chargesid") > 0) {
			HashMap<String, String> hashMap2 = new HashMap<String, String>();

			hashMap2.put("name", name);
			hashMap2.put("email", email);
			// 更新处理状态
			Integer ofHandle = failInfoMapper.updateStateOfHandle(hashMap2);
			if (ofHandle > 0) {
				Map<String, String> zp = chargesService.sendMailZP(map.get("chargesid"), failInfo.getLang());
				return zp;
			} else {
				hashMap.put("state", "1");
			}
			return hashMap;
		}
		hashMap.put("state", "1");
		return hashMap;
	}

	public Map<String, Object> verifySign(String sign) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		Integer findBySign = chargesMapper.findBySign(sign);
		if (findBySign > 0) {
			Charges data = chargesMapper.selectChargesBySign(sign);
			hashMap.put("state", "0");
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("ticket_no", ""+data.getChargesId());
			map.put("name", data.getName());
			hashMap.put("status", "0");
			hashMap.put("data", map);
			return hashMap;
		}
		hashMap.put("state", "1");
		hashMap.put("status", "1");

		return hashMap;
	}

	public MailInfo findMailInfo(String name) {
		MailInfo mail = mailInfoMapper.selectMail(name);
		return mail;
	}

	public Map<String, String> sendAllAgain() {
		ExecutorService pool = Executors.newFixedThreadPool(10);
		HashMap<String, String> hashMap = new HashMap<String, String>();
		List<Charges> allCharges = chargesMapper.findAllCharges();
		Integer acount = 0;
		for (Charges charges : allCharges) {
			SendAll sendAll = new SendAll();
			sendAll.setCharges(charges);
			pool.execute(sendAll);
			acount += 1;
		}
		hashMap.put("account", "" + acount);
		return hashMap;

	}

	class SendAll implements Runnable {
		private Charges charges;

		public Charges getCharges() {
			return charges;
		}

		public void setCharges(Charges charges) {
			this.charges = charges;
		}

		public void run() {
			// 创建门票
			String file = Graphies.creatFile(charges.getZxingcodepath(), charges.getLang(), "" + charges.getChargesId(),
					charges.getName());
			Mail mail = new Mail(charges.getEmail(), null, null, new Date(), new File(file));

			// 发送邮件
			MailUtils mailUtils = new MailUtils();
			MailInfo payMail = mailInfoMapper.selectMail("pay");
			MailInfo payMailCN = mailInfoMapper.selectMail("payCN");

			mailUtils.setSubjectEn(payMail.getSubject());
			mailUtils.setContentEn(payMail.getContent());
			mailUtils.setSubjectCn(payMailCN.getSubject());
			mailUtils.setContentCn(payMailCN.getContent());
			boolean sendMessage = mailUtils.sendMessage(mail, charges.getName(), charges.getLang());
		}

	}

	

}
