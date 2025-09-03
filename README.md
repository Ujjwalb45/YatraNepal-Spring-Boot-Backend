# YatraNepal API - Spring Boot

A comprehensive tourism platform API converted from Express.js to Spring Boot with integrated Swagger documentation.

## Features

- **Authentication**: JWT-based user registration and login
- **Hotels**: Hotel management with search and filtering
- **Places**: Tourist destination management with geolocation
- **Swagger Documentation**: Complete API documentation at `/swagger-ui.html`
- **MongoDB Integration**: NoSQL database with Spring Data MongoDB
- **CORS Support**: Cross-origin resource sharing configuration
- **Security**: Spring Security with JWT authentication

## Technology Stack

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data MongoDB**
- **Spring Security**
- **JWT (JSON Web Tokens)**
- **Swagger/OpenAPI 3**
- **Maven**

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- MongoDB (local or cloud instance)

### Installation

1. Clone the repository
2. Navigate to the api-springboot directory
3. Configure environment variables in `application.yml`:
   ```yaml
   spring:
     data:
       mongodb:
         uri: mongodb://localhost:27017/yatranepal
   
   jwt:
     secret: your-secret-key
   ```

4. Build and run the application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

### Environment Variables

Create a `.env` file or set the following environment variables:

- `MONGO_URI`: MongoDB connection string
- `JWT_SECRET`: Secret key for JWT token generation
- `FRONTEND_URL`: Frontend application URL for CORS
- `MAIL_HOST`: SMTP server host
- `MAIL_USERNAME`: Email username
- `MAIL_PASSWORD`: Email password

## API Documentation

Once the application is running, access the Swagger UI at:
- **Swagger UI**: http://localhost:8080/api/swagger-ui.html
- **API Docs**: http://localhost:8080/api/api-docs

## API Endpoints

### Authentication
- `POST /auth/register` - Register new user
- `POST /auth/login` - User login
- `GET /auth/` - Check authentication status

### Hotels
- `GET /hotels` - Get all hotels (with filtering)
- `GET /hotels/{id}` - Get hotel by ID
- `POST /hotels` - Create new hotel
- `PUT /hotels/{id}` - Update hotel
- `DELETE /hotels/{id}` - Delete hotel
- `GET /hotels/search` - Search hotels by name

### Places
- `GET /place` - Get all places (with filtering)
- `GET /place/{id}` - Get place by ID
- `POST /place` - Create new place
- `PUT /place/{id}` - Update place
- `DELETE /place/{id}` - Delete place
- `GET /place/search` - Search places by name
- `GET /place/nearby` - Get nearby places

### Health Check
- `GET /health` - API health status

## Project Structure

```
src/main/java/com/yatranepal/api/
├── YatraNepalApiApplication.java    # Main application class
├── config/                          # Configuration classes
│   ├── CorsConfig.java
│   ├── SecurityConfig.java
│   └── SwaggerConfig.java
├── controller/                      # REST controllers
│   ├── AuthController.java
│   ├── HotelController.java
│   ├── PlaceController.java
│   └── HealthController.java
├── dto/                            # Data Transfer Objects
│   ├── AuthRequest.java
│   ├── AuthResponse.java
│   └── RegisterRequest.java
├── model/                          # Entity classes
│   ├── User.java
│   ├── Hotel.java
│   ├── Room.java
│   └── Place.java
├── repository/                     # Repository interfaces
│   ├── UserRepository.java
│   ├── HotelRepository.java
│   └── PlaceRepository.java
└── service/                        # Service classes
    ├── AuthService.java
    ├── JwtService.java
    ├── HotelService.java
    └── PlaceService.java
```

## Conversion Notes

This Spring Boot API is converted from the original Express.js implementation with the following improvements:

1. **Type Safety**: Strong typing with Java
2. **Dependency Injection**: Spring's IoC container
3. **Auto-configuration**: Spring Boot's auto-configuration
4. **Integrated Documentation**: Swagger/OpenAPI 3 annotations
5. **Security**: Spring Security integration
6. **Validation**: Bean validation with annotations
7. **Error Handling**: Structured error responses

## Development

To extend the API:

1. Add new entities in the `model` package
2. Create corresponding repositories in the `repository` package
3. Implement business logic in the `service` package
4. Create REST endpoints in the `controller` package
5. Add Swagger annotations for documentation

## Testing

Run tests with:
```bash
mvn test
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests
5. Submit a pull request

# YatraNepal-Spring-Boot-Backend