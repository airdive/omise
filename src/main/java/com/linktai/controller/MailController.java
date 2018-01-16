package com.linktai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.linktai.pojo.Mail;
import com.linktai.utils.MailUtils;

@Controller
public class MailController {
	@Autowired
	private MailUtils mailUtils;
	public boolean sendmail(Mail mail) {
		boolean message = mailUtils.sendMessage(mail);
		return message;
	}
}
