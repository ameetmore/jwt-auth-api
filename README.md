# 🔐 JWT Auth API

A production-ready JWT-based authentication and authorization REST API built with Spring Boot 3 and Spring Security.

## Features
- User registration & login
- JWT token generation and validation
- Role-based access control (USER / ADMIN)
- BCrypt password hashing
- Stateless session management

## Tech Stack
- Java 17, Spring Boot 3.2.5, Spring Security, JJWT, H2 (swap for PostgreSQL)

## Endpoints
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Login and receive JWT |
| GET | `/api/auth/hello` | Protected endpoint |

## Run
```bash
mvn spring-boot:run
```
Access H2 Console: http://localhost:8081/h2-console

## Docker Commands:

-docker build -t jwt-auth-api .
-docker run -p 8081:8081 jwt-auth-api
