# RentVideo – Secure Video Rental REST API

RentVideo is a backend RESTful service built with **Spring Boot** that simulates an online video rental system. The application provides secure authentication using **JWT tokens**, role-based authorization, and supports renting and returning videos with business rules enforced at the service layer.

This project demonstrates modern backend development practices including layered architecture, stateless authentication, and relational database design.

---

## Tech Stack

- Java 17
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA (Hibernate)
- MySQL
- Gradle
- REST API Architecture

---

## Core Features

### User Authentication
- User registration
- Secure password storage using **BCrypt**
- JWT based login authentication
- Stateless API authentication

### Role Based Authorization

Two system roles are supported:

- **ADMIN**
- **CUSTOMER**

Permissions:

| Role | Permissions |
|-----|-------------|
ADMIN | Create, update, delete videos |
CUSTOMER | Browse videos, rent videos, return videos |

---

## Video Management

The system allows managing video inventory.

Each video contains:

- Title
- Director
- Genre
- Availability Status

Features:

- View all videos
- Add new videos (Admin only)
- Update video details (Admin only)
- Delete videos (Admin only)

---

## Rental Management

Users can rent and return videos.

Business rules enforced:

- A user can have **maximum 2 active rentals**
- Videos must exist before renting
- Users can only return videos they have rented

---

## API Endpoints

### Authentication

#### Register User

POST /auth/register

Example Request:

```json
{
  "email": "user@example.com",
  "password": "password123",
  "firstName": "John",
  "lastName": "Doe",
  "role": "CUSTOMER"
}
```

---

#### Login

POST /auth/login

Response:

```json
{
  "token": "JWT_TOKEN"
}
```

Use the token in headers:

```
Authorization: Bearer JWT_TOKEN
```

---

## Video APIs

### Get All Videos

GET /videos

### Create Video (Admin Only)

POST /videos

### Update Video (Admin Only)

PUT /videos/{id}

### Delete Video (Admin Only)

DELETE /videos/{id}

---

## Rental APIs

### Rent Video

POST /rentals/videos/{videoId}/rent

### Return Video

POST /rentals/videos/{videoId}/return

---

## Project Architecture

The project follows a layered architecture for separation of concerns.

```
controller
    AuthController
    VideoController
    RentalController

service
    UserService
    VideoService
    RentalService

repository
    UserRepository
    VideoRepository
    RentalRepository

model
    User
    Video
    Rental
    Role

security
    SecurityConfig
    JwtService
    JwtFilter
    CustomUserDetailsService
```

---

## Database Design

### User

| Field | Description |
|------|-------------|
id | Primary key |
email | User email |
password | Encrypted password |
firstName | First name |
lastName | Last name |
role | ADMIN / CUSTOMER |

### Video

| Field | Description |
|------|-------------|
id | Primary key |
title | Video title |
director | Director name |
genre | Video genre |
available | Availability status |

### Rental

| Field | Description |
|------|-------------|
id | Primary key |
user_id | Renting user |
video_id | Rented video |
rented_at | Rental timestamp |
returned | Rental status |

---

## Running the Project

### 1. Clone the repository

```
git clone https://github.com/YOUR_USERNAME/rentvideo-system.git
```

### 2. Configure MySQL

Create database:

```
CREATE DATABASE rentvideo;
```

Update `application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/rentvideo
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
```

### 3. Run the application

```
./gradlew bootRun
```

Server will start at:

```
http://localhost:8080
```

---

## Security Design

The application uses **JWT based stateless authentication**.

Authentication flow:

1. User registers
2. User logs in
3. Server generates JWT token
4. Client includes token in request headers
5. JWT filter validates token before accessing secured endpoints

---

## Future Improvements

Possible enhancements:

- Pagination for video listing
- Rental history tracking
- Video availability management
- Global exception handling
- Unit and integration testing
- API documentation using Swagger

---

## Author

Rishabh 
Backend Developer | Java & Spring Boot
