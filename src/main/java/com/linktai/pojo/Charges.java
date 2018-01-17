package com.linktai.pojo;

import java.util.Date;

public class Charges {
    private Integer chargesId;

    private Integer accountId;

    private Integer ticketId;

    private Integer chargesRental;

    private Date chargesTime;

    private Integer chargesState;

    private Integer issendmail;

    private String chargesNumberOmise;

    private String zxingcodepath;

    private String sign;

    private Integer isused;
    
    private String cardNumber;

    public Integer getChargesId() {
        return chargesId;
    }

    public void setChargesId(Integer chargesId) {
        this.chargesId = chargesId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
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

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
    
    
}