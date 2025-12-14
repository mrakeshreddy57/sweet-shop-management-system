🍬 Sweet Shop Management System

A full-stack Sweet Shop Management System built using Spring Boot (Backend) and React + Vite (Frontend).
The application supports user authentication, role-based access, sweet inventory management, and purchase workflows, developed using Test-Driven Development (TDD) principles.

🚀 Features
Backend (Spring Boot)

User registration & login with JWT authentication

Role-based authorization (Admin / User)

CRUD operations for sweets

Search sweets by name, category, and price range

Inventory management:

Purchase sweets (quantity decreases)

Restock sweets (Admin only)

Global exception handling

Persistent database (non in-memory)

Unit & integration tests

Frontend (React)

User login & registration

View and search sweets

Purchase sweets (disabled when out of stock)

Admin-only UI for add, update, delete, and restock

Responsive UI using Tailwind CSS

🛠 Tech Stack

Backend

Java 17

Spring Boot

Spring Security + JWT

JPA / Hibernate

PostgreSQL (or configured DB)

JUnit & Mockito

Frontend

React

Vite

Tailwind CSS

Axios

🧪 Test-Driven Development (TDD)

This project follows a TDD approach:

Tests were written before implementing functionality

Development followed the Red → Green → Refactor cycle

Meaningful unit tests for service and controller layers

Clear commit history narrating development progress

Run Backend Tests
./mvnw test

⚙️ Local Setup Instructions
Backend Setup
cd sweet-shop-api
./mvnw spring-boot:run


Ensure database credentials are set in application.properties.

Frontend Setup
cd sugar-shop-ui
npm install
npm run dev


Frontend runs at:

http://localhost:5173


Backend runs at:

http://localhost:8080

📸 Screenshots

Add screenshots of:

Login page

Sweet list

Admin dashboard

Purchase flow

(Place screenshots inside a /screenshots folder and reference them here.)

🤖 My AI Usage
AI Tools Used

ChatGPT

How I Used AI

Brainstorming API design and folder structure

Generating initial boilerplate for controllers and services

Drafting unit test templates

Improving code readability and documentation

My Reflection

AI significantly improved my productivity by reducing boilerplate effort and helping validate design decisions.
All business logic, validations, and architectural decisions were reviewed, modified, and finalized by me.
AI was used as a development assistant, not as a replacement for understanding or ownership.

📄 Test Report

All tests pass successfully.
Test coverage focuses on:

Authentication logic

Sweet inventory operations

Purchase and restock scenarios

Authorization rules

(Test results can be verified by running ./mvnw test.)

🌍 Deployment (Optional)

Not deployed yet.

👤 Author

Rakesh Reddy
