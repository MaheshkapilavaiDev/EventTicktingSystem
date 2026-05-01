package com.eventticktingsystem.service;

import java.time.LocalDateTime;
import java.util.Random;

import com.eventticktingsystem.model.Payment;
import com.eventticktingsystem.util.FileUtil;

public class PaymentService {
	public Payment process(double amount) {
		boolean success = new Random().nextInt(10) != 0;
		Payment p = new Payment("TXN" + System.currentTimeMillis(), amount, "UPI", success ? "SUCCESS" : "FAILED",
				LocalDateTime.now().toString());
		FileUtil.write("data/payments.txt", p.toString());
		return p;
	}
}
