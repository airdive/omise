package com.linktai.service;

import java.util.Map;

import com.linktai.pojo.Charges;
import com.linktai.pojo.Managers;
import com.linktai.utils.PageUtil;

public interface ManagerService {
	Managers login(Managers managers);
	boolean updatePWD(Managers managers);
	
	PageUtil<Charges> pageListQD(Integer cp, Integer ps, String select);
	
	Map<String,Integer> accountQD();
}
