🛫 Flight Booking System (Spring Boot)

A RESTful Flight Booking API built with Java Spring Boot, designed to handle flights, bookings, passengers, and payments in an airline reservation-style system. This project demonstrates a modular backend service with CRUD operations to manage core airline booking workflows.

This README assumes your code follows typical Spring Boot conventions with controllers, services, repositories, and entities.

📦 Features

Create & Manage Flights

Create & Retrieve Bookings

Manage Passengers

Track Payments

REST endpoints using Spring MVC

Data persistence using Spring Data JPA

Standard project structure for scalable APIs

🧠 Architecture Overview

This project splits core functionality into logical services:

Flight API — Create, update, fetch, and delete flight records

Booking API — Create bookings, list bookings by ID, date, flight, status

Passenger API — Add and retrieve passenger details

Payment tracking — Payment is assigned to bookings automatically on creation

You could think of it as a mini airline GDS-style API. 
GitHub

🛠️ Tech Stack
Component	Technology
Backend	Java, Spring Boot
ORM	Spring Data JPA
Build	Maven
Database	(configurable — e.g., H2/MySQL/PostgreSQL)
API	REST Controllers
Java	17+ recommended
🚀 Getting Started
1. Clone the Repo
git clone https://github.com/Mahesh-656/Flight_Booking.git
cd Flight_Booking

2. Customize application.properties

Configure your database:

spring.datasource.url=jdbc:mysql://localhost:3306/flight_booking
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update


You can also use an in-memory DB like H2 for quick local testing.

3. Build & Run
mvn clean install
mvn spring-boot:run


The API will start at:

http://localhost:8080

📍 Core REST Endpoints (Example)
Flight
POST /flights
GET /flights
GET /flights/{id}
PUT /flights/{id}
DELETE /flights/{id}

Booking
POST /bookings
GET /bookings
GET /bookings/{id}
GET /bookings/flight/{flightId}

Passenger
POST /passengers
GET /passengers
GET /passengers/{id}

Payments
GET /payments
GET /payments/{id}
GET /payments/booking/{bookingId}


(Exact paths may vary based on your controller annotations — adjust accordingly.)

🧩 Example Workflow

Create a Flight

Create a Booking for that flight

Passengers are added automatically via booking

Payment record created with the booking

Retrieve bookings, flights, payment, and passenger details

This mirrors how an airline backend would handle a booking lifecycle.

🧪 API Testing

Use tools like Postman
