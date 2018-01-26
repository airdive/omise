package com.linktai.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linktai.dao.ChargesMapper;
import com.linktai.dao.FailInfoMapper;
import com.linktai.dao.MailInfoMapper;
import com.linktai.dao.ManagersMapper;
import com.linktai.pojo.Charges;
import com.linktai.pojo.FailInfo;
import com.linktai.pojo.MailInfo;
import com.linktai.pojo.Managers;
import com.linktai.service.IChargesService;
import com.linktai.service.ManagerService;
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

	public Map<String, String> verifySign(String sign) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		boolean contains = sign.contains(",");
		// 前期签名没有加编号
		if (!contains) {
			hashMap.put("state", "0");
			return hashMap;
		}
		int indexOf = sign.indexOf(',');
		String chargesId = sign.substring(0, indexOf);
		String s = sign.substring(indexOf + 1, sign.length());
		Integer valueOf = null;
		try {
			valueOf = Integer.valueOf(chargesId);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			hashMap.put("state", "1");
			return hashMap;
		}
		HashMap<String,Integer> map = new HashMap<String, Integer>();
		map.put("chargesId", valueOf);
		Charges charges = chargesMapper.findChargesByChargesId(map);
		String numberOmise = charges.getChargesNumberOmise();
		String encript = RsaUtils.encript(numberOmise, RsaUtils.public_key);
		boolean verify = RsaUtils.verify(encript, s, RsaUtils.public_key);
		if(verify) {
			hashMap.put("state", "0");
		}else {
			hashMap.put("state", "1");
		}
		return hashMap;
	}

	

	public MailInfo findMailInfo(String name) {
		MailInfo mail = mailInfoMapper.selectMail(name);
		return mail;
	}

}
