package com.linktai.pojo;

import java.util.Date;

public class Mail {
	//ÊÕ¼şÈË
	private String recever;
	private String subject;
	private StringBuilder content;
	private Date sendDate;
	public Mail() {
		// TODO Auto-generated constructor stub
	}
	
	public Mail(String recever, String subject, StringBuilder content, Date sendDate) {
		super();
		this.recever = recever;
		this.subject = subject;
		this.content = content;
		this.sendDate = sendDate;
	}

	public String getRecever() {
		return recever;
	}
	public void setRecever(String recever) {
		this.recever = recever;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public StringBuilder getContent() {
		return content;
	}
	public void setContent(StringBuilder content) {
		this.content = content;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	
}
