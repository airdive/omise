package com.linktai.service;

import java.util.Map;

import com.linktai.pojo.CardOfAc;
import com.linktai.pojo.Charges;
import com.linktai.utils.PageUtil;

public interface IChargesService {
	final static String PUBLIC_KEY = "pkey_5af2oawcv1t31tqg8xg";
	final static String PRIVATE_KEY = "skey_5af2osj87h3wu6v1cfp";

	PageUtil<Charges> listPage(Integer cp, Integer ps, String select);

	Map<String, String> charges(String arg, CardOfAc card1);

	Map<String, Integer> charges(Charges charges);
	
	Map<String, String> updateChargesInfo(Charges charges);
	
	Charges refound(Integer chargesId);
	
	boolean delete(Integer chargesId);

}
