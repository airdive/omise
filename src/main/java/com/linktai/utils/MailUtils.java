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

		// 创建邮件
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(myEmailAccount, "linktime", "utf-8"));
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(cMailAccount, "cc", "utf-8"));
			message.setSubject("这是主题");
			
			//正文
			MimeBodyPart text = new MimeBodyPart();
			text.setContent("<h1>这是内容位置</h1>", "text/html;charset=UTF-8");
			//附件
			MimeBodyPart attachment = new MimeBodyPart();
			DataHandler dh2 = new DataHandler(new FileDataSource("D:/tickets7.png")); // 读取本地文件
			attachment.setDataHandler(dh2); // 将附件数据添加到“节点”
			attachment.setFileName(MimeUtility.encodeText(dh2.getName()));

			MimeMultipart mm = new MimeMultipart();
			mm.addBodyPart(text);
			mm.addBodyPart(attachment); // 如果有多个附件，可以创建多个多次添加
			mm.setSubType("mixed"); // 混合关系

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
