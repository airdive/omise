package com.linktai.service;

import java.util.Map;

import com.linktai.pojo.CardOfAc;
import com.linktai.pojo.Charges;
import com.linktai.utils.PageUtil;

public interface IChargesService {
	final static String PUBLIC_KEY = "pkey_test_56bod6t9yl5li6whpfa";
	final static String PRIVATE_KEY = "skey_test_56bod6tahnicxrvrxxq";

	PageUtil<Charges> listPage(Integer cp, Integer ps, String select);

	Map<String, String> charges(String arg, CardOfAc card1);

	Map<String, Integer> charges(Charges charges);
	
	Map<String, String> updateChargesInfo(Charges charges);
	
	Charges refound(Integer chargesId);
	
	boolean delete(Integer chargesId);

}
