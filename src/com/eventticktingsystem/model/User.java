package com.eventticktingsystem.model;

import java.util.*;

public class User {
	private String userId, name, email, password;
	private List<String> ticketIds = new ArrayList<>();

	public User(String userId, String name, String email, String password, List<String> ticketIds) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.ticketIds = ticketIds;
	}

	public User(String userId, String name, String email, String password) {
		this(userId, name, email, password, new ArrayList<>());
	}

	public String getUserId() {
		return userId;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public List<String> getTicketIds() {
		return ticketIds;
	}

	@Override
	public String toString() {
		String tickets = String.join(";", ticketIds);
		return userId + "," + name + "," + email + "," + password + "," + tickets;
	}

	public static User fromString(String line) {
		String[] d = line.split(",");
		List<String> tickets = new ArrayList<>();
		if (d.length > 4 && !d[4].isEmpty()) {
			tickets = Arrays.asList(d[4].split(";"));
		}
		return new User(d[0], d[1], d[2], d[3], tickets);
	}
}
