package com.linktai.pojo;

import java.util.Date;

public class Charges {
	private Integer chargesId;

	private Integer ticketId;

	private Integer chargesRental;

	private Date chargesTime;
	//����״̬��ʶ�����ڼ�¼�Ƿ���ڣ���ѯʱ�ã�0��ʾδ���ڣ�1��ʾ����
	private Integer chargesState;

	private Integer issendmail;

	private String chargesNumberOmise;

	private String zxingcodepath;

	private String sign;

	private Integer isused;

	private String cardnumber;

	private String name;

	private String country;

	private String company;

	private String position;

	private String email;

	private String telephone;

	private String note;
	
	private String lang;

	public Charges() {
		// TODO Auto-generated constructor stub
	}

	public Charges(Integer ticketId, Integer chargesRental, Date chargesTime, Integer chargesState, Integer issendmail,
			String chargesNumberOmise, String zxingcodepath, String sign, Integer isused) {
		super();
		this.ticketId = ticketId;
		this.chargesRental = chargesRental;
		this.chargesTime = chargesTime;
		this.chargesState = chargesState;
		this.issendmail = issendmail;
		this.chargesNumberOmise = chargesNumberOmise;
		this.zxingcodepath = zxingcodepath;
		this.sign = sign;
		this.isused = isused;
	}

	public Integer getChargesId() {
		return chargesId;
	}

	public void setChargesId(Integer chargesId) {
		this.chargesId = chargesId;
	}

	public Integer getTicketId() {
		return ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}

	public Integer getChargesRental() {
		return chargesRental;
	}

	public void setChargesRental(Integer chargesRental) {
		this.chargesRental = chargesRental;
	}

	public Date getChargesTime() {
		return chargesTime;
	}

	public void setChargesTime(Date chargesTime) {
		this.chargesTime = chargesTime;
	}

	public Integer getChargesState() {
		return chargesState;
	}

	public void setChargesState(Integer chargesState) {
		this.chargesState = chargesState;
	}

	public Integer getIssendmail() {
		return issendmail;
	}

	public void setIssendmail(Integer issendmail) {
		this.issendmail = issendmail;
	}

	public String getChargesNumberOmise() {
		return chargesNumberOmise;
	}

	public void setChargesNumberOmise(String chargesNumberOmise) {
		this.chargesNumberOmise = chargesNumberOmise;
	}

	public String getZxingcodepath() {
		return zxingcodepath;
	}

	public void setZxingcodepath(String zxingcodepath) {
		this.zxingcodepath = zxingcodepath;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Integer getIsused() {
		return isused;
	}

	public void setIsused(Integer isused) {
		this.isused = isused;
	}

	public String getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	@Override
	public String toString() {
		return "Charges [chargesId=" + chargesId + ", ticketId=" + ticketId + ", chargesRental=" + chargesRental
				+ ", chargesTime=" + chargesTime + ", chargesState=" + chargesState + ", issendmail=" + issendmail
				+ ", chargesNumberOmise=" + chargesNumberOmise + ", zxingcodepath=" + zxingcodepath + ", sign=" + sign
				+ ", isused=" + isused + ", cardnumber=" + cardnumber + ", name=" + name + ", country=" + country
				+ ", company=" + company + ", position=" + position + ", email=" + email + ", telephone=" + telephone
				+ ", note=" + note + "]";
	}
	
	
}