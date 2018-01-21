package com.linktai.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.junit.Test;
import org.springframework.stereotype.Component;

import com.linktai.pojo.Mail;

@Component
public class MailUtils {
	// �����˺�,����
	public static String myEmailAccount = "meetup@linktimetech.com";
	public static String myEmailPassword = "LinkTime123456";
	public static String myEmailSMTPHost = "smtp.exmail.qq.com";
	public static String subjectEn ="Payment Confirmation and E-ticket for Asia-Pacific Ethereum Community Meetup";
	public static String ContentEn ="Hi, \r\n" + 
			"\r\n" + 
			"Thanks for your support to Asia-Pacific Ethereum Community Meetup!\r\n" + 
			"We have received your payment, and attached is your personal E-ticket. Please note that the E-ticket is very important because with that you can get your personal name badge as the pass during the event. Each ticket can only be used once by the holder himself/herself, it shall not be sent, altered or transfered. \r\n" + 
			"With any questions, please email at: meetup@linktimetech.com\r\n" + 
			"Updates will be announced in the following channels, and Don't forget to join our communities! :)\r\n" + 
			"\r\n" + 
			"Website: https://baoming.in/\r\n" + 
			"Telegram: https://t.me/AsiaETHfans\r\n" + 
			"\r\n" + 
			"Again, thank you for your participation. Have a nice day!\r\n" + 
			"\r\n" + 
			"Best regards\r\n" + 
			"LinkTime\r\n";
	public static String subjectCn ="亚太以太坊社区Meet up的门票信息";
	public static String ContentCn ="您好！\r\n" + 
			"\r\n" + 
			"非常感谢您对亚太以太坊社区Meet up的支持！\r\n" + 
			"我们已经收到您的付款，附件是您个人的电子门票，门票仅限本人使用。请注意保管好您的门票并于入场时出示该门票。\r\n" + 
			"如果您有任何问题，请发邮件到meetup@linktimetech.com进行咨询。\r\n" + 
			"\r\n" + 
			"网站: https://baoming.in/\r\n" + 
			"Telegram: https://t.me/AsiaETHfans\r\n" + 
			"LinkTime\r\n" + 
			"";
	


	public static boolean sendMessage(Mail mail,String name,String lang) {
		Properties props = new Properties(); // ��������
		props.setProperty("mail.transport.protocol", "smtp"); // ʹ�õ�Э�飨JavaMail�淶Ҫ��
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.host", myEmailSMTPHost); // �����˵������ SMTP ��������ַ
		props.setProperty("mail.smtp.auth", "true"); // ��Ҫ������
		
		final String smtpPort = "465";
		props.setProperty("mail.smtp.port",smtpPort);
        props.setProperty("mail.smtp.port",smtpPort);
        props.setProperty("mail.smtp.socketFactory.fallback","false");
        props.setProperty("mail.smtp.socketFactory.port",smtpPort);
		props.setProperty("mail.smtp.ssl.enable","true");
		Session session = Session.getInstance(props);
		//session.setDebug(true);
		// �����ʼ�
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(myEmailAccount, "linktime", "utf-8"));
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(mail.getRecever(), name, "utf-8"));
			message.setSubject(lang.equals("en")?subjectEn:subjectCn);
			
			//����
			MimeBodyPart text = new MimeBodyPart();
			text.setText(lang.equals("en")?ContentEn:ContentCn);
			//����
			MimeBodyPart attachment = new MimeBodyPart();
			DataHandler dh2 = new DataHandler(new FileDataSource((mail.getFiles()))); // ��ȡ�����ļ�
			attachment.setDataHandler(dh2); // ������������ӵ����ڵ㡱
			attachment.setFileName(MimeUtility.encodeText(dh2.getName()));

			MimeMultipart mm = new MimeMultipart();
			mm.addBodyPart(text);
			mm.addBodyPart(attachment); // ����ж�����������Դ������������
			mm.setSubType("mixed"); // ��Ϲ�ϵ

			message.setContent(mm);
			message.setSentDate(new Date());
			message.saveChanges();

			Transport transport = session.getTransport();
			transport.connect(myEmailAccount, myEmailPassword);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			return true;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Test
	public void test1() {
		Mail mail = new Mail("571740367@qq.com", "","", new Date(), new File("D:/ticket.png"));
		boolean sendMessage = sendMessage(mail,"as",null);
		System.out.println(sendMessage);
	}

}
