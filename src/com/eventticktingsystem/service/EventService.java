package com.eventticktingsystem.service;

import java.util.List;

import com.eventticktingsystem.model.Event;
import com.eventticktingsystem.model.Ticket;

public class EventService {
	private List<Event> events;

	public EventService(List<Event> events) {
		this.events = events;
	}

	public void createEvent(Event e) {
		events.add(e);
	}

	public List<Event> getAllEvents() {
		return events;
	}

	public Event getById(String id) {
		return events.stream().filter(e -> e.getEventId().equals(id)).findFirst().orElse(null);
	}

	public boolean delete(String id, List<Ticket> tickets) {
		boolean hasBooking = tickets.stream().anyMatch(t -> t.getEventId().equals(id));
		if (hasBooking) {
			System.out.println("Cannot delete event with bookings!");
			return false;
		}
		return events.removeIf(e -> e.getEventId().equals(id));
	}
	
	public List<Event> searchByCategory(String category) {
	    return events.stream()
	            .filter(e -> e.getCategory().equalsIgnoreCase(category))
	            .toList();
	}

	public List<Event> searchByLocation(String location) {
	    return events.stream()
	            .filter(e -> e.getLocation().equalsIgnoreCase(location))
	            .toList();
	}
	
	
}
