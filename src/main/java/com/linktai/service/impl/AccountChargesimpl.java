package com.linktai.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linktai.dao.AccountInfoMapper;
import com.linktai.dao.ChargesMapper;
import com.linktai.pojo.AccountInfo;
import com.linktai.pojo.CardOfAc;
import com.linktai.service.IAccountCharges;

import co.omise.Client;
import co.omise.ClientException;
import co.omise.models.Card;
import co.omise.models.Charge;
import co.omise.models.OmiseException;
import co.omise.models.Token;
import co.omise.models.Token.Create;
@Service
public class AccountChargesimpl implements IAccountCharges{
	@Autowired
	private AccountInfoMapper accountInfoMapper;
	@Autowired
	private ChargesMapper chargesMapper;
	public boolean charges(AccountInfo accountInfo, CardOfAc card1) {
		try {
			Client client = new Client(PUBLIC_KEY,PRIVATE_KEY);
			//银行卡信息
			Create card = new Token.Create().card(new Card.Create()
	                .name(card1.getNameOnCard())
	                .number(card1.getCardNumber())
	                .expiration(card1.getExpiryMonth(), card1.getExpiryYear())
	                .securityCode(card1.getSecurityCode())
	        );
			Token token = client.tokens().create(card);
			//新加坡币
			//发起一个交易
			Charge charge = client.charges().create(new Charge.Create().amount(100000) // THB 1,000.00
					.currency("sgd").card(token.getId()));
			//交易成功，存数据入数据库
			int insert = accountInfoMapper.insert(accountInfo);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OmiseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
}
