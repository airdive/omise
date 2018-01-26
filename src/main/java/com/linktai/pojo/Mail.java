package com.linktai.pojo;

import java.io.File;
import java.util.Date;

public class Mail {
	
	private String recever;
	private String subject;
	private String content;
	private Date sendDate;
	private File files;
	public Mail() {
		// TODO Auto-generated constructor stub
	}
	
	public Mail(String recever, String subject, String content, Date sendDate,File files) {
		super();
		this.recever = recever;
		this.subject = subject;
		this.content = content;
		this.sendDate = sendDate;
		this.files = files;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public File getFiles() {
		return files;
	}

	public void setFiles(File files) {
		this.files = files;
	}
	
	
}
