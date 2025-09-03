# YatraNepal API - Spring Boot with PostgreSQL

Successfully converted Express.js API to Spring Boot with PostgreSQL integration.

## ✅ **Conversion Complete**

### **Database Migration: MongoDB → PostgreSQL**
- Updated all entity models with JPA annotations
- Changed ID types from String to Long with auto-generation
- Replaced MongoDB repositories with JPA repositories
- Updated queries from MongoDB syntax to JPQL/SQL

### **Configuration Changes**
- **application.properties** instead of application.yml
- PostgreSQL driver and connection configuration
- JPA/Hibernate settings with DDL auto-update
- Auditing enabled for created/updated timestamps

### **Entity Updates**
- `@Entity` and `@Table` annotations
- `@Id` with `@GeneratedValue(strategy = GenerationType.IDENTITY)`
- `@Column` constraints for unique fields
- `@OneToMany`, `@ManyToOne` relationships
- `@Embedded` and `@ElementCollection` for nested objects

### **Quick Start**

1. **Install PostgreSQL**
   ```bash
   # Create database
   createdb yatranepal
   ```

2. **Update connection in application.properties**
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/yatranepal
   spring.datasource.username=postgres
   spring.datasource.password=your_password
   ```

3. **Run Application**
   ```bash
   mvn spring-boot:run
   ```

### **API Endpoints**
- **Swagger UI**: `http://localhost:8080/api/swagger-ui.html`
- **Health Check**: `http://localhost:8080/api/health`
- **Auth**: `/api/auth/register`, `/api/auth/login`
- **Hotels**: `/api/hotels` (CRUD + search)
- **Places**: `/api/place` (CRUD + geolocation)

### **Database Schema**
PostgreSQL tables will be auto-created:
- `users` - User accounts with authentication
- `hotels` - Hotel information and pricing
- `rooms` - Hotel rooms with availability
- `places` - Tourist destinations with coordinates
- `room_numbers` - Room availability tracking

### **Key Features**
- ✅ JWT Authentication
- ✅ PostgreSQL with JPA/Hibernate
- ✅ Swagger Documentation
- ✅ Input Validation
- ✅ CORS Configuration
- ✅ Error Handling
- ✅ Audit Timestamps

The application is ready to run with PostgreSQL!
