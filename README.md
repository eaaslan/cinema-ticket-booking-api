# Cinema Room REST Service

## Overview
This project is a RESTful service for a cinema room that allows users to view available seats, purchase tickets, return tickets, and view statistics about the cinema's sales and available seats. It's built using Java with Spring Boot, providing a solid foundation for both simplicity and scalability.

## Features
- **View Available Seats**: Users can get information about available seats in the cinema.
- **Purchase Tickets**: Users can purchase tickets for available seats. Each purchase returns a unique token for the ticket.
- **Return Tickets**: Tickets can be returned using the token received during the purchase, making the seat available again.
- **View Statistics**: Admin users can view statistics such as the current income, number of available seats, and the number of purchased tickets.

## Technologies
- Java
- Spring Boot
- Gradle

## Getting Started

### Prerequisites
- JDK 11 or later
- Gradle 6.3 or later (if not using the Gradle Wrapper)

### Running the Application
1. Clone the repository:
git clone https://github.com/yourusername/cinema-room-rest-service.git

2. Navigate to the project directory:
cd cinema-room-rest-service

3. Run the application using Gradle:
./gradlew bootRun
