# User Management API

## Overview

User Management API is a RESTful service built with Spring Boot for basic user lifecycle operations.
The project implements a layered architecture with input validation and centralized exception handling.

## Objectives

- Provide CRUD operations for user records.
- Enforce validation rules for request payloads.
- Return consistent HTTP status codes and error responses.
- Maintain separation of concerns through controller, service, repository, DTO, model, and exception layers.

## Technology Stack

- Java 17+
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Jakarta Bean Validation
- H2 Database (runtime, in-memory)
- Maven

## Project Structure

```text
src/main/java/com/orfeu/usermanager
|- controller
|- dto
|- exception
|- model
|- repository
|- service
```

## Domain Model

Entity: User

- id: Long (primary key, auto-generated)
- name: String (required)
- email: String (required, unique)
- password: String (required)

## Validation Rules

The API validates request payloads using Jakarta Validation annotations:

- Name: must not be blank
- Email: must not be blank and must be a valid email format
- Password: must not be blank

## Error Handling

Global exception handling is implemented with ControllerAdvice.

- 400 Bad Request: validation failures and duplicate email attempts
- 404 Not Found: user does not exist

Standard error response fields for business exceptions:

- status
- message
- timestamp

## API Endpoints

Base URL:

```text
http://localhost:8080
```

| Method | Endpoint      | Description          |
|--------|---------------|----------------------|
| POST   | /users        | Create a new user    |
| GET    | /users        | List all users       |
| PUT    | /users/{id}   | Update an existing user |
| DELETE | /users/{id}   | Delete an existing user |

## Request and Response Examples

Create User

```http
POST /users
Content-Type: application/json

{
  "name": "Ana Silva",
  "email": "ana@example.com",
  "password": "123456"
}
```

```json
{
  "id": 1,
  "name": "Ana Silva",
  "email": "ana@example.com"
}
```

Validation Error Example

```http
POST /users
Content-Type: application/json

{
  "name": "",
  "email": "invalid-email",
  "password": ""
}
```

```json
{
  "name": "Name is required",
  "email": "Invalid email format",
  "password": "Password is required"
}
```

User Not Found Example

```json
{
  "status": 404,
  "message": "User not found with id: 999",
  "timestamp": "2026-03-31T10:00:00"
}
```

## Running the Application

1. Build the project:

```bash
mvn clean install
```

2. Start the application:

```bash
mvn spring-boot:run
```

3. Access H2 Console (optional):

```text
http://localhost:8080/h2-console
```

## Configuration

Default datasource and JPA configuration is defined in application.properties.
For external databases (for example PostgreSQL or MySQL), replace datasource values and driver dependency accordingly.

## Testing

Execute tests with:

```bash
mvn test
```

Sanity testing can be performed with Insomnia or Postman using the endpoints listed above.
