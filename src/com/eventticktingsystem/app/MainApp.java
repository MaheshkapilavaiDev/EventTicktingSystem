package com.eventticktingsystem.app;

import java.util.*;

import com.eventticktingsystem.model.*;
import com.eventticktingsystem.service.*;
import com.eventticktingsystem.util.FileUtil;

public class MainApp {

	static List<Event> events = new ArrayList<>();
	static List<User> users = new ArrayList<>();
	static List<Ticket> tickets = new ArrayList<>();

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		// Load data
		FileUtil.read("data/events.txt").forEach(l -> events.add(Event.fromString(l)));
		FileUtil.read("data/users.txt").forEach(l -> users.add(User.fromString(l)));
		FileUtil.read("data/tickets.txt").forEach(l -> tickets.add(Ticket.fromString(l)));

		EventService eventService = new EventService(events);
		UserService userService = new UserService(users);
		TicketService ticketService = new TicketService(tickets);
		ReportService reportService = new ReportService();
		AnalyticsService analyticsService = new AnalyticsService(tickets, events);

		while (true) {

			System.out.println("\n==== MAIN MENU ====");
			System.out.println("1. Admin");
			System.out.println("2. User");
			System.out.println("3. Exit");

			int ch = sc.nextInt();

			switch (ch) {

			// ================= ADMIN =================
			case 1:
				System.out.print("Enter username: ");
				String u = sc.next();
				sc.nextLine();
				System.out.print("Enter password: ");
				String p = sc.next();
				sc.nextLine();

				if (u.equals("admin") && p.equals("admin123")) {

					while (true) {
						System.out.println("\n--- ADMIN MENU ---");
						System.out.println("1. Create Event");
						System.out.println("2. View Events");
						System.out.println("3. Delete Event");
						System.out.println("4. Generate Report");
						System.out.println("5. Back");
						System.out.println("6. Most Booked Event");

						// int a = sc.nextInt();
						int a = Integer.parseInt(sc.nextLine());
						// sc.nextLine();

						if (a == 1) {
							System.out.print("Event Name: ");
							String name = sc.nextLine();

							System.out.print("Category: ");
							String cat = sc.nextLine();

							System.out.print("Date: ");
							String date = sc.nextLine();

							System.out.print("Time: ");
							String time = sc.nextLine();

							System.out.print("Location: ");
							String loc = sc.nextLine();

							System.out.print("Total Seats: ");
							int ts = Integer.parseInt(sc.nextLine());

							System.out.print("Price: ");
							double price = Double.parseDouble(sc.nextLine());

							Event e = new Event(UUID.randomUUID().toString(), name, cat, date, time, loc, ts, ts,
									price);

							eventService.createEvent(e);
							System.out.println("✅ Event created!");
						}

						else if (a == 2) {
							eventService.getAllEvents().forEach(System.out::println);
						}

						else if (a == 3) {
							System.out.print("Enter Event ID: ");
							String id = sc.nextLine();
							eventService.delete(id, tickets);
						}

						else if (a == 4) {
							System.out.print("Enter Event ID: ");
							String id = sc.nextLine();
							reportService.generateEventReport(id, tickets);
						}
						else if (a == 6) {

						    String eventId = analyticsService.getMostBookedEventName();

						    Event event = eventService.getById(eventId);

						    if (event == null) {
						        System.out.println("❌ Event not found for ID: " + eventId);
						    } else {
						        System.out.println("📊 Most Booked Event:");
						        System.out.println("ID: " + eventId);
						        System.out.println("Name: " + event.getEventName());
						    }
						}

						else if (a == 5)
							break;
					}
				} else {
					System.out.println("❌ Invalid Admin Credentials");
				}
				break;

			// ================= USER =================
			case 2:
				
				sc.nextLine();
				
				System.out.print("Email: ");
				String email = sc.nextLine();

				System.out.print("Password: ");
				String pass = sc.nextLine();

				User user = userService.login(email, pass);

				if (user == null) {

					if (userService.emailExists(email)) {
						System.out.println("❌ Incorrect password!");
						continue;
					}

					System.out.println("Registering new user...");
					user = new User(UUID.randomUUID().toString(), "User", email, pass);
					userService.register(user);
				}

				while (true) {
					System.out.println("\n--- USER MENU ---");
					System.out.println("1. View Events");
					System.out.println("2. Search Events");
					System.out.println("3. Book Ticket");
					System.out.println("4. Cancel Ticket");
					System.out.println("5. Back");

					int uc = Integer.parseInt(sc.nextLine());

					if (uc == 1) {
						System.out.println("\n--- EVENTS LIST ---");
						eventService.getAllEvents().forEach(System.out::println);
					}

					else if (uc == 2) {
						System.out.println("1. Category 2. Location");
						int s = Integer.parseInt(sc.nextLine());

						if (s == 1) {
							System.out.print("Enter Category: ");
							String cat = sc.nextLine();
							eventService.searchByCategory(cat).forEach(System.out::println);
						}

						if (s == 2) {
							System.out.print("Enter Location: ");
							String loc = sc.nextLine();
							eventService.searchByLocation(loc).forEach(System.out::println);
						}
					}

					else if (uc == 3) {

						System.out.println("\nAvailable Events:");
						eventService.getAllEvents().forEach(System.out::println);

						System.out.print("Enter Event ID: ");
						String id = sc.nextLine().trim(); // ✅ FIX

						Event e = eventService.getById(id);

						if (e == null) {
							System.out.println("❌ Event not found!");
							continue;
						}

						System.out.print("Enter number of seats: ");
						int seats = Integer.parseInt(sc.nextLine()); // ✅ FIX

						ticketService.book(user, e, seats);
					}

					else if (uc == 4) {
						System.out.print("Enter Ticket ID: ");
						String tid = sc.nextLine(); // ✅ FIX
						ticketService.cancel(tid, events);
					}

					else if (uc == 5)
						break;
				}
				break;

			// ================= EXIT =================
			case 3:
				FileUtil.overwrite("data/events.txt", events.stream().map(Event::toString).toList());
				FileUtil.overwrite("data/users.txt", users.stream().map(User::toString).toList());
				FileUtil.overwrite("data/tickets.txt", tickets.stream().map(Ticket::toString).toList());

				System.out.println("💾 Data saved. Exiting...");
				System.exit(0);
			}
		}
	}
}