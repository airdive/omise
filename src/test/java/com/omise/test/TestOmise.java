package com.omise.test;

import java.io.IOException;

import org.junit.Test;

import co.omise.Client;
import co.omise.ClientException;
import co.omise.models.Account;
import co.omise.models.Card;
import co.omise.models.Charge;
import co.omise.models.OmiseException;
import co.omise.models.Refund;
import co.omise.models.Token;
import co.omise.models.Token.Create;

public class TestOmise {
	private final static String PUBLIC_KEY="pkey_test_5an82rjt7doisbuipmv";
	private final static String PRIVATE_KEY="skey_test_5an82rjtq9mefhky391";
	
	public static void main(String[] args) {
		try {
			Client client = new Client("pkey_test_5an82rjt7doisbuipmv", "skey_test_5an82rjtq9mefhky391");
			Account account = client.account().get();
			String id = account.getId();
			String email = account.getEmail();
			String location = account.getLocation();
			System.out.println(location);
			System.out.println(id);
			System.out.println(email);

			long total = client.balance().get().getTotal();
			System.out.println("余额：" + total);
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
	}
	//交易测试
	@Test
	public void test1() {
		try {
			Client client = new Client(PUBLIC_KEY,PRIVATE_KEY);
			//银行卡信息
			Create card = new Token.Create().card(new Card.Create()
	                .name("JOHN DOE")
	                .number("4242424242424242")
	                .expiration(1, 2020)
	                .securityCode("123")
	        );
			Token token = client.tokens().create(card);
			System.out.println(token.getId());
			//新加坡币
			//发起一个交易
			Charge charge = client.charges().create(new Charge.Create().amount(100000) // THB 1,000.00
					.currency("sgd").card(token.getId()));
			
			System.out.println("created charge: " + charge.getId());
			
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
	}
	//退款测试
	@Test
	public void test2() {
		Client client;
		try {
			client = new Client(PUBLIC_KEY,PRIVATE_KEY);
			//根据订单编号退款
			Refund refund = client.charge("chrg_test_5anaadb9wm611rni7s8")
					.refunds().create(new Refund.Create()
							.amount(10000));
			System.out.println("created refund: %s"+ refund.getId());
			System.out.println("退款成功");
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
	}
	

}
