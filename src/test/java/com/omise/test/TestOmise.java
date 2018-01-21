package com.omise.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

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
	final static String PUBLIC_KEY = "pkey_test_56bod6t9yl5li6whpfa";
	final static String PRIVATE_KEY = "skey_test_56bod6tahnicxrvrxxq";
	
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
			System.out.println("��" + total);
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
	//���ײ���
	@Test
	public void test1() {
		try {
			Client client = new Client(PUBLIC_KEY,PRIVATE_KEY);
			//���п���Ϣ
			Create card = new Token.Create().card(new Card.Create()
	                .name("JOHN DOE")
	                .number("4242424242424242")
	                .expiration(1, 2020)
	                .securityCode("123")
	        );
			Token token = client.tokens().create(card);
			System.out.println(token.getId());
			//�¼��±�
			//����һ������
			Charge charge = client.charges().create(new Charge.Create().amount(100) // THB 1,000.00
					.currency("usd").card(token.getId()));
			
			System.out.println("created charge: " + charge.getId());
			
		} catch (ClientException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (OmiseException e) {
			e.printStackTrace();
		}
	}
	//�˿����
	@Test
	public void test2() {
		Client client;
		try {
			client = new Client(PUBLIC_KEY,PRIVATE_KEY);
			//���ݶ�������˿�
			Refund refund = client.charge("chrg_test_5anaadb9wm611rni7s8")
					.refunds().create(new Refund.Create()
							.amount(10000));
			System.out.println("created refund: %s"+ refund.getId());
			System.out.println("�˿�ɹ�");
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
	
	@Test
	public void test3() {
		UUID randomUUID = UUID.randomUUID();
		System.out.println(randomUUID.toString());
		File directory = new File("");//设定为当前文件夹 
		try{ 
		    System.out.println(directory.getCanonicalPath());//获取标准的路径 
		    System.out.println(directory.getAbsolutePath());//获取绝对路径 
		}catch(Exception e){} 
		
	}
	

}
