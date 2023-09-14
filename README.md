# E-Assets by Team JDBC

The E-Asset Management System is a web-based application designed to help a company manage its assets effectively. It allows employees to borrow and return company assets, while administrators can manage assets, users, overdue cases, and messaging.

## Features

- User Management:
  - User registration and login.
  - Admin can view and manage user accounts.
  - Users can borrow and return assets.

- Asset Management:
  - Admin can add, view, and manage assets.
  - Users can view available assets and borrow them.

- Overdue Management:
  - Admin can view overdue cases.
  - Admin can ban users and charge fines.

- Messaging:
  - Admin can send messages to overdue users.
  - Users can view messages.

## Technologies Used

- Java (for backend services)
- MySQL (for the database)
- UML (for system design)
- Plain HTML, CSS, JavaScript for UI

## Project Structure

The project is organized into several packages and classes, including:
- `Asset`, `User`, `Transaction`, `Message` classes representing core data entities.
- Service layer classes for asset, overdue, and user management.
- MySQL database tables for data storage.

## Setup

1. Clone this repository to your local machine.
2. Set up the database using the provided SQL scripts.
3. Configure your preferred web framework to work with the project.
4. Start the web application and access it via a web browser.

## Usage

- As an admin, you can log in to manage assets, users, overdue cases, and send messages.
- As a regular user, you can log in to borrow and return assets and view messages.

