package com.bookity.domain.payment;


public class CardInfo {

	public String number;
	public String name;
	public String expiresAt;

	public CardInfo(String number, String name, String expiresAt) {
		this.number = number;
		this.name = name;
		this.expiresAt = expiresAt;
	}
}