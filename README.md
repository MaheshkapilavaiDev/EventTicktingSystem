# Event Ticketing System (Java)

## 📌 Overview
This is a console-based Event Ticketing System built using Core Java, Collections, and File I/O.

## 🚀 Features
- Admin:
  - Create, view, delete events
  - Generate event reports

- User:
  - Register & Login
  - View & search events
  - Book tickets
  - Cancel tickets (70% refund)

- Payment Simulation:
  - Random failure (10%)
  - Payment logs stored in file

- Receipt Generation:
  - Ticket receipt with QR string

## 📂 Project Structure
- model → Data classes
- service → Business logic
- util → File handling
- app → Main application
- data → Text files

## ▶️ How to Run
1. Open project in IDE
2. Run MainApp.java
3. Use console menu

## 📁 Data Files
Located in `/data`:
- events.txt
- users.txt
- tickets.txt
- payments.txt

## 📄 Sample Output
- receipt_<ticketId>.txt
- event_report_<eventId>.txt

## ✅ Author
Mahesh Kumar