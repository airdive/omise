package com.linktai.service;

import java.util.Map;

import com.linktai.pojo.Charges;
import com.linktai.pojo.MailInfo;
import com.linktai.pojo.Managers;
import com.linktai.utils.PageUtil;

public interface ManagerService {
	Managers login(Managers managers);
	boolean updatePWD(Managers managers);
	
	PageUtil<Charges> pageListQD(Integer cp, Integer ps, String select);
	
	Map<String,Integer> accountQD();
	
	Map<String, String> updateMailContent(MailInfo mailInfo);
	
	Map<String, Integer> countNotRead();
	
	Map<String, String> updateReadState();
	
	Map<String, String> addFailInfoToCharges(Integer id,String name,String email);
	
	Map<String, Object> verifySign(String sign);
	
	MailInfo findMailInfo(String name);
	
	Map<String, String> sendAllAgain();
}
