package com.linktai.pojo;

public class MailInfo {
    private String name;

	private String subject;

	private String content;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "MailInfo [name=" + name + ", subject=" + subject + ", content=" + content + "]";
	}

	
}