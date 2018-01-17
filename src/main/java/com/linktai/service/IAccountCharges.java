package com.linktai.service;

import com.linktai.pojo.AccountInfo;
import com.linktai.pojo.Card;

public interface IAccountCharges {
	public boolean charges(AccountInfo accountInfo,Card card) ;
}
