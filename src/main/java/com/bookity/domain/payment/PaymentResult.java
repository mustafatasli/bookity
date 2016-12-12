package com.bookity.domain.payment;


public class PaymentResult {

	public boolean succeeded;
	public String refID;
	public String request;
	public String response;

	public PaymentResult(boolean succeeded, String refID,
		String request, String response) {
		this.succeeded = succeeded;
		this.refID = refID;
		this.request = request;
		this.response = response;
	}
}