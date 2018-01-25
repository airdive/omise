package com.linktai.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linktai.dao.FailInfoMapper;
import com.linktai.pojo.FailInfo;
import com.linktai.service.IFailInfoService;
import com.linktai.utils.PageUtil;
@Service
public class FailInfoServiceimpl implements IFailInfoService{
	@Autowired
	private FailInfoMapper failInfoMapper;
	public Map<String, Integer> countFailNuber() {
		Integer count = failInfoMapper.findAllCount("failed_fraud_check");
		Integer count2 = failInfoMapper.findAllCount("invalid_security_code");
		Integer count3 = failInfoMapper.findAllCount("payment_rejected");
		Integer count4 = failInfoMapper.findAllCount(null);
		HashMap<String,Integer> hashMap = new HashMap<String, Integer>();
		hashMap.put("failed_fraud_check", count);
		hashMap.put("invalid_security_code", count2);
		hashMap.put("payment_rejected", count3);
		hashMap.put("allCount", count4);
		return hashMap;
	}

	public PageUtil<FailInfo> pagespilt(Integer cp, Integer ps,String failCode) {
		Integer allCount = failInfoMapper.findAllCount(failCode);
		Integer allPage = (allCount + ps - 1) / ps;
		Integer start = cp * ps;
		HashMap<String,Object> hashMap = new HashMap<String, Object>();
		hashMap.put("start", start);
		hashMap.put("ps", ps);
		hashMap.put("failCode", failCode);
		List<FailInfo> info = failInfoMapper.findFailInfo(hashMap);
		for (FailInfo failInfo : info) {
			failInfo.setDateFormat();
		}
		PageUtil<FailInfo> pageUtil = new PageUtil<FailInfo>(info, cp, ps, allPage);
		return pageUtil;
	}
	
}
