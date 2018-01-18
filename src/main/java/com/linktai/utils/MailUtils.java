package com.linktai.utils;

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
	public static String myEmailAccount = "17770039942@163.com";
	public static String myEmailPassword = "asdasd123123";
	public static String myEmailSMTPHost = "smtp.163.com";

	public static String cMailAccount = "571740367@qq.com";

	public static boolean sendMessage(Mail mail) {
		Properties props = new Properties(); // ��������
		props.setProperty("mail.transport.protocol", "smtp"); // ʹ�õ�Э�飨JavaMail�淶Ҫ��
		props.setProperty("mail.smtp.host", myEmailSMTPHost); // �����˵������ SMTP ��������ַ
		props.setProperty("mail.smtp.auth", "true"); // ��Ҫ������֤
		Session session = Session.getInstance(props);
		//session.setDebug(true);

		// �����ʼ�
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(myEmailAccount, "linktime", "utf-8"));
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(cMailAccount, "cc", "utf-8"));
			message.setSubject(mail.getSubject());
			
			//����
			MimeBodyPart text = new MimeBodyPart();
			text.setContent(mail.getContent(), "text/html;charset=UTF-8");
			//����
			MimeBodyPart attachment = new MimeBodyPart();
			DataHandler dh2 = new DataHandler(new FileDataSource(mail.getFiles())); // ��ȡ�����ļ�
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
		Mail mail = new Mail();
		boolean sendMessage = sendMessage(mail);
		System.out.println(sendMessage);
	}

}
