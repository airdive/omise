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
	public String subjectEn;
	public String ContentEn;
	public String subjectCn;
	public String ContentCn;
	
	

	public  String getSubjectEn() {
		return subjectEn;
	}

	public  void setSubjectEn(String subjectEn) {
		this.subjectEn = subjectEn;
	}

	public  String getContentEn() {
		return ContentEn;
	}

	public  void setContentEn(String contentEn) {
		ContentEn = contentEn;
	}
	
	

	public String getSubjectCn() {
		return subjectCn;
	}

	public void setSubjectCn(String subjectCn) {
		this.subjectCn = subjectCn;
	}

	public String getContentCn() {
		return ContentCn;
	}

	public void setContentCn(String contentCn) {
		ContentCn = contentCn;
	}

	public  boolean sendMessage(Mail mail,String name,String lang) {
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
