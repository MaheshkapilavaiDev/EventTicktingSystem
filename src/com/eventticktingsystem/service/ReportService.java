package com.eventticktingsystem.service;

import java.util.*;
import java.util.stream.Collectors;

import com.eventticktingsystem.model.Ticket;
import com.eventticktingsystem.util.FileUtil;

public class ReportService {
	public void generateEventReport(String eventId, List<Ticket> tickets) {

		List<Ticket> eventTickets = tickets.stream().filter(t -> eventId.equals(t.getEventId())).toList();

		double totalRevenue = eventTickets.stream().mapToDouble(Ticket::getTotalAmount).sum();

		int totalSeats = eventTickets.stream().mapToInt(Ticket::getNumberOfSeats).sum();

		StringBuilder report = new StringBuilder();
		report.append("---- EVENT REPORT ----\n");
		report.append("Event ID: ").append(eventId).append("\n");
		report.append("Total Bookings: ").append(eventTickets.size()).append("\n");
		report.append("Total Seats Booked: ").append(totalSeats).append("\n");
		report.append("Total Revenue: ").append(totalRevenue).append("\n\n");

		if (eventTickets.isEmpty()) {
			report.append("No bookings found for this event.\n");
		} else {
			for (Ticket t : eventTickets) {
				report.append(t.toString()).append("\n");
			}
		}

		FileUtil.write("data/event_report_" + eventId + ".txt", report.toString());

		System.out.println("Report generated!");
	}
}
