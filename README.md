#  Video Rental System API

A secure RESTful backend service built using **Spring Boot** to manage an online video rental system.  
The application supports **user registration, authentication, role-based authorization, and video management** while persisting data in **MySQL**.

This project demonstrates backend development practices including **Spring Security, REST API design, layered architecture, and database integration using JPA**.

---

##  Features

- User Registration with **BCrypt password hashing**
- **Basic Authentication** using Spring Security
- **Role-based authorization** with two roles: ADMIN and CUSTOMER
- Video management APIs (Create, Update, Delete, Browse)
- MySQL database persistence
- Layered architecture (Controller → Service → Repository)
- Secure RESTful API endpoints

---

##  Tech Stack

| Technology | Purpose |
|------------|---------|
Spring Boot | Backend framework |
Spring Security | Authentication & Authorization |
Spring Data JPA | ORM and database interaction |
MySQL | Relational database |
Gradle | Build tool |
BCrypt | Secure password hashing |

---

##  Project Structure

```
src/main/java/com/project2/rentvideo

controller
   └── AuthController
   └── VideoController

model
   └── User
   └── Video
   └── Role

repository
   └── UserRepository
   └── VideoRepository

service
   └── UserService
   └── VideoService

security
   └── SecurityConfig
   └── CustomUserDetailsService
```

---

##  Security Implementation

The API uses **Spring Security with Basic Authentication** to authenticate users.

Supported Roles:

```
ADMIN
CUSTOMER
```

Authorization rules:

| Endpoint | Access |
|--------|--------|
POST /auth/register | Public |
GET /videos | Authenticated Users |
POST /videos | ADMIN only |
PUT /videos/{id} | ADMIN only |
DELETE /videos/{id} | ADMIN only |

Passwords are securely stored using **BCrypt hashing**.

---

## 🗄 Database Schema

### Users Table

| Field | Type |
|------|------|
id | Long |
email | String |
password | String (BCrypt encrypted) |
firstName | String |
lastName | String |
role | ENUM (ADMIN / CUSTOMER) |

### Videos Table

| Field | Type |
|------|------|
id | Long |
title | String |
director | String |
genre | String |
available | Boolean |

---

## ⚙️ Running the Project

### 1️ Clone the repository

```
git clone https://github.com/YOUR_USERNAME/rentvideo-system.git
```

### 2️ Configure MySQL database

Create database:

```
CREATE DATABASE rentvideo;
```

Update `application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/rentvideo
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

### 3️ Run the application

```
./gradlew bootRun
```

Server will start on:

```
http://localhost:8080
```

---

##  Example API Requests

### Register User

```
POST /auth/register
```

Request Body:

```json
{
  "email": "user@test.com",
  "password": "123456",
  "firstName": "John",
  "lastName": "Doe"
}
```

---

### Get All Videos

```
GET /videos
```

Requires **Basic Authentication**.

---

### Create Video (ADMIN only)

```
POST /videos
```

Example request body:

```json
{
  "title": "Inception",
  "director": "Christopher Nolan",
  "genre": "Sci-Fi",
  "available": true
}
```

---

## Future Improvements

- JWT based authentication
- Video rental and return feature
- Pagination and search for videos
- Docker containerization
- Unit and integration testing

---

## Author

**Rishabh**  
Backend Developer | Java | Spring Boot
