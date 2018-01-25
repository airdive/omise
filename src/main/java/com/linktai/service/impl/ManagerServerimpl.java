package com.linktai.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linktai.dao.ChargesMapper;
import com.linktai.dao.ManagersMapper;
import com.linktai.pojo.Charges;
import com.linktai.pojo.Managers;
import com.linktai.service.ManagerService;
import com.linktai.utils.PageUtil;

@Service
public class ManagerServerimpl implements ManagerService {
	@Autowired
	private ManagersMapper managersMapper;
	@Autowired
	private ChargesMapper chargesMapper;

	public Managers login(Managers managers) {
		if (managers != null) {
			Managers login = managersMapper.login(managers);
			return login;
		}
		return null;
	}

	public boolean updatePWD(Managers managers) {
		Integer updatePWD = managersMapper.updatePWD(managers);
		return updatePWD>0?true:false;
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
		HashMap<String,Object> hashMap = new HashMap<String, Object>();
		hashMap.put("select", null);
		Integer allCount = chargesMapper.findAllCount(hashMap);
		Integer countZP = chargesMapper.findAllCountZP(hashMap);
		Integer all = allCount + countZP;
		Integer countQD = chargesMapper.findAllCountQD(hashMap);
		HashMap<String,Integer> map = new HashMap<String, Integer>();
		map.put("allCount", all);
		map.put("countQD", countQD);
		return map;
	}

}
