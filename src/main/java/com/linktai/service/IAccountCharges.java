package com.linktai.service;

import com.linktai.pojo.AccountInfo;
import com.linktai.pojo.CardOfAc;

public interface IAccountCharges {
	final static String PUBLIC_KEY="pkey_test_5an82rjt7doisbuipmv";
	final static String PRIVATE_KEY="skey_test_5an82rjtq9mefhky391";
	public boolean charges(AccountInfo accountInfo,CardOfAc card) ;
}
