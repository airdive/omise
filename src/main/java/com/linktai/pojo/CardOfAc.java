package com.linktai.pojo;

public class CardOfAc {
	private String nameOnCard;
	private String cardNumber;
	private Integer expiryMonth;
	private Integer expiryYear;
	private String securityCode;
	public String getNameOnCard() {
		return nameOnCard;
	}
	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public Integer getExpiryMonth() {
		return expiryMonth;
	}
	public void setExpiryMonth(Integer expiryMonth) {
		this.expiryMonth = expiryMonth;
	}
	public Integer getExpiryYear() {
		return expiryYear;
	}
	public void setExpiryYear(Integer expiryYear) {
		this.expiryYear = expiryYear;
	}
	public String getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	@Override
	public String toString() {
		return "CardOfAc [nameOnCard=" + nameOnCard + ", cardNumber=" + cardNumber + ", expiryMonth=" + expiryMonth
				+ ", expiryYear=" + expiryYear + ", securityCode=" + securityCode + "]";
	}
	
	
}
