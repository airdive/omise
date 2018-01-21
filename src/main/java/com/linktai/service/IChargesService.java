package com.linktai.service;

import java.util.Map;

import com.linktai.pojo.CardOfAc;
import com.linktai.pojo.Charges;
import com.linktai.utils.PageUtil;

public interface IChargesService {
	final static String PUBLIC_KEY = "pkey_test_5an82rjt7doisbuipmv";
	final static String PRIVATE_KEY = "skey_test_5an82rjtq9mefhky391";

	PageUtil<Charges> listPage(Integer cp, Integer ps, String select);

	Map<String, String> charges(String arg, CardOfAc card1);

	Map<String, Integer> charges(Charges charges);
	
	Map<String, String> updateChargesInfo(Charges charges);
	
	Charges refound(Integer chargesId);
	
	boolean delete(Integer chargesId);

}
