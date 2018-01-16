package com.linktai.utils;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

import com.linktai.pojo.Mail;
@Component
public class MailUtils {
	// 发送账号,密码
	public static String myEmailAccount = "17770039942@163.com";
	public static String myEmailPassword = "asdasd123123";
	public static String myEmailSMTPHost = "smtp.163.com";

	public static String cMailAccount = "571740367@qq.com";

	public boolean sendMessage(Mail mail) {
		Properties props = new Properties(); // 参数配置
		props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
		props.setProperty("mail.smtp.host", myEmailSMTPHost); // 发件人的邮箱的 SMTP 服务器地址
		props.setProperty("mail.smtp.auth", "true"); // 需要请求认证
		Session session = Session.getInstance(props);
		session.setDebug(true);
		MimeMessage message = creatmessage(session, mail);
		Transport transport = null;
		try {
			transport = session.getTransport();
			transport.connect(myEmailAccount, myEmailPassword);
			transport.sendMessage(message, message.getAllRecipients());
			return true;
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} finally {
			try {
				transport.close();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static MimeMessage creatmessage(Session session, Mail mail) {
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(myEmailAccount, "linktime", "UTF-8"));
			message.setRecipient(MimeMessage.RecipientType.TO,
					new InternetAddress(mail.getRecever(), "USER_CC", "UTF-8"));
			message.setSubject(mail.getSubject(), "UTF-8");
			message.setContent(mail.getContent(), "text/html;charset=UTF-8");
			message.setSentDate(mail.getSendDate());
			message.saveChanges();
			return message;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} 
		return null;
	}
}
