package com.eventticktingsystem.model;

public class Ticket {
	private String ticketId, eventId, userId, bookingDateTime, status;
	private int numberOfSeats;
	private double totalAmount;

	public Ticket(String ticketId, String eventId, String userId, int numberOfSeats, String bookingDateTime,
			String status, double totalAmount) {
		this.ticketId = ticketId;
		this.eventId = eventId;
		this.userId = userId;
		this.numberOfSeats = numberOfSeats;
		this.bookingDateTime = bookingDateTime;
		this.status = status;
		this.totalAmount = totalAmount;
	}

	public String getTicketId() {
		return ticketId;
	}

	public String getEventId() {
		return eventId;
	}

	public String getUserId() {
		return userId;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public String getStatus() {
		return status;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return ticketId + "," + eventId + "," + userId + "," + numberOfSeats + "," + bookingDateTime + "," + status
				+ "," + totalAmount;
	}

	public static Ticket fromString(String line) {
		String[] d = line.split(",");
		return new Ticket(d[0], d[1], d[2], Integer.parseInt(d[3]), d[4], d[5], Double.parseDouble(d[6]));
	}
}