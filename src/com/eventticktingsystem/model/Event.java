package com.eventticktingsystem.model;

public class Event {
	private String eventId, eventName, category, date, time, location;
	private int totalSeats, availableSeats;
	private double ticketPrice;

	public Event(String eventId, String eventName, String category, String date, String time, String location,
			int totalSeats, int availableSeats, double ticketPrice) {
		this.eventId = eventId;
		this.eventName = eventName;
		this.category = category;
		this.date = date;
		this.time = time;
		this.location = location;
		this.totalSeats = totalSeats;
		this.availableSeats = availableSeats;
		this.ticketPrice = ticketPrice;
	}

	public String getEventId() {
		return eventId;
	}

	public String getEventName() { 
		return eventName;
	}

	public String getCategory() {
		return category;
	}

	public String getLocation() {
		return location;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	@Override
	public String toString() {
		return eventId + "," + eventName + "," + category + "," + date + "," + time + "," + location + "," + totalSeats
				+ "," + availableSeats + "," + ticketPrice;
	}

	public static Event fromString(String line) {
		String[] d = line.split(",");
		return new Event(d[0], d[1], d[2], d[3], d[4], d[5], Integer.parseInt(d[6]), Integer.parseInt(d[7]),
				Double.parseDouble(d[8]));
	}

}