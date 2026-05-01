package com.eventticktingsystem.service;

import java.util.*;

import com.eventticktingsystem.model.Event;
import com.eventticktingsystem.model.Payment;
import com.eventticktingsystem.model.Ticket;
import com.eventticktingsystem.model.User;
import com.eventticktingsystem.util.FileUtil;
import com.eventticktingsystem.util.ReceiptUtil;

public class TicketService {

	private List<Ticket> tickets;
	private PaymentService paymentService = new PaymentService();

	public TicketService(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket book(User user, Event event, int seats) {

		if (seats <= 0 || seats > event.getAvailableSeats()) {
			System.out.println("Invalid seats!");
			return null;
		}

		double amount = seats * event.getTicketPrice();
		Payment payment = paymentService.process(amount);

		if (payment.getStatus().equals("FAILED")) {
			System.out.println("Payment Failed!");
			return null;
		}

		event.setAvailableSeats(event.getAvailableSeats() - seats);

		Ticket t = new Ticket(UUID.randomUUID().toString(), event.getEventId(), user.getUserId(), seats,
				java.time.LocalDateTime.now().toString(), "BOOKED", amount);

		tickets.add(t);
		FileUtil.write("data/tickets.txt", t.toString());

		//  ADD RECEIPT
		ReceiptUtil.generate(t, event, payment);

		// LINK TO USER
		user.getTicketIds().add(t.getTicketId());

		return t;
	}

	public void cancel(String ticketId, List<Event> events) {

		Ticket ticket = tickets.stream().filter(t -> t.getTicketId().equals(ticketId)).findFirst().orElse(null);

		if (ticket == null) {
			System.out.println("❌ Ticket not found!");
			return;
		}

		if (ticket.getStatus().equals("CANCELED")) {
			System.out.println("❌ Already canceled!");
			return;
		}

		// Update status
		ticket.setStatus("CANCELED");

		// Restore seats
		Event event = events.stream().filter(e -> e.getEventId().equals(ticket.getEventId())).findFirst().orElse(null);

		if (event != null) {
			event.setAvailableSeats(event.getAvailableSeats() + ticket.getNumberOfSeats());
		}

		// Refund 70%
		double refund = ticket.getTotalAmount() * 0.7;

		System.out.println("✅ Ticket canceled");
		System.out.println("💰 Refund: " + refund);
	}
}