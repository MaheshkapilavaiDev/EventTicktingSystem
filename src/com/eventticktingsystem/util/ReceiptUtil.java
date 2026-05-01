package com.eventticktingsystem.util;

import java.util.UUID;

import com.eventticktingsystem.model.Event;
import com.eventticktingsystem.model.Payment;
import com.eventticktingsystem.model.Ticket;

public class ReceiptUtil {

	public static void generate(Ticket ticket, Event event, Payment payment) {

		String qr = UUID.randomUUID().toString().substring(0, 10);

		String content = "------ TICKET RECEIPT ------\n" + "Ticket ID: " + ticket.getTicketId() + "\n" + "Event ID: "
				+ event.getEventId() + "\n" + "Seats: " + ticket.getNumberOfSeats() + "\n" + "Amount: "
				+ ticket.getTotalAmount() + "\n" + "Payment Status: " + payment.getStatus() + "\n" + "Transaction ID: "
				+ payment.getTransactionId() + "\n" + "QR Code: " + qr + "\n";

		FileUtil.write("data/receipt_" + ticket.getTicketId() + ".txt", content);
	}
}
