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

	public String getMostBookedEventId() {

	    Map<String, Long> count = tickets.stream()
	        .filter(t -> "BOOKED".equals(t.getStatus()))
	        .collect(Collectors.groupingBy(
	            Ticket::getEventId,
	            Collectors.counting()
	        ));

	    return count.entrySet().stream()
	        .max(Map.Entry.comparingByValue())
	        .map(Map.Entry::getKey)
	        .orElse(null);
	}
}