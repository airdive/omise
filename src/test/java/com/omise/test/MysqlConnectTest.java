package com.omise.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.junit.Test;

public class MysqlConnectTest {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/meetup?serverTimezone=Hongkong", "root", "root");
			Statement statement = connection.createStatement();
			boolean execute = statement.execute("INSERT INTO edcon_charges (ticket_id,name,charges_rental) VALUES (1,\"中国\",100)");
			
			System.out.println("插入完成");
			return;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("����ʧ��");
	}
	
	@Test
	public void test() {
		Date date = new Date();
		System.out.println(date);
	}
}
