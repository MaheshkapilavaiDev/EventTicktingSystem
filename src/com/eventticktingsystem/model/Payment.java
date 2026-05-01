package com.eventticktingsystem.model;

public class Payment {
	private String transactionId, paymentMethod, status, timestamp;
	private double amount;

	public Payment(String transactionId, double amount, String paymentMethod, String status, String timestamp) {
		this.transactionId = transactionId;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public String getTransactionId() {
		return transactionId;
	}

	@Override
	public String toString() {
		return transactionId + "," + amount + "," + paymentMethod + "," + status + "," + timestamp;
	}
}
