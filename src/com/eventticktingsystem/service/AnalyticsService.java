package com.eventticktingsystem.service;

import java.util.*;
import java.util.stream.*;

import com.eventticktingsystem.model.Event;
import com.eventticktingsystem.model.Ticket;

public class AnalyticsService {

	private List<Ticket> tickets;

	private List<Event> events;

	public AnalyticsService(List<Ticket> tickets, List<Event> events) {
		this.tickets = tickets;
		this.events = events;
	}

	public String getMostBookedEventName() {

		Map<String, Long> count = tickets.stream().filter(t -> t.getStatus().equals("BOOKED"))
				.collect(Collectors.groupingBy(Ticket::getEventId, Collectors.counting()));

		String eventId = count.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey)
				.orElse(null);

		if (eventId == null) {
			return "No bookings found";
		}

		// 🔍 find event name
		return events.stream().filter(e -> e.getEventId().equals(eventId)).map(Event::getEventName).findFirst()
				.orElse("Unknown Event");
	}
}