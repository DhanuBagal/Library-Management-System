# Library Management System

## Project Overview

The **Library Management System** is a full-stack web application built with **Spring Boot** (backend) and **Angular** (frontend) that enables efficient management of library resources. The system allows users to manage books, authors, users, and track book issuance/return activities.

### Key Features
- **Book Management**: Add, update, delete, and view books with detailed information
- **Author Management**: Manage authors and their relationships with books
- **User Management**: Handle user registrations and role-based access control
- **Book Issuance Tracking**: Track which books have been issued to users and when they were returned
- **Role-Based Access Control**: Support for Admin and Member roles with different permissions
- **User Authentication**: Login functionality with email and password validation

---

## Technology Stack

### Backend
- **Framework**: Spring Boot 3.5.4
- **Language**: Java 21
- **Database**: MySQL
- **Build Tool**: Maven
- **Security**: Spring Security
- **API**: RESTful Web Services with CORS support

### Frontend
- **Framework**: Angular 21
- **Language**: TypeScript 5.9
- **CSS Framework**: Bootstrap 5.3
- **UI Components**: Angular Material
- **Package Manager**: npm 10.8.2

---

## Prerequisites

Before running this project, ensure you have the following installed:

### For Backend
- **Java 21** or higher ([Download Java](https://www.oracle.com/java/technologies/downloads/))
- **Apache Maven** 3.6+ ([Download Maven](https://maven.apache.org/download.cgi))
- **MySQL Server 8.0+** ([Download MySQL](https://www.mysql.com/downloads/))

### For Frontend
- **Node.js 18+** ([Download Node.js](https://nodejs.org/))
- **npm 10.8.2** or higher (included with Node.js)
- **Angular CLI 21.x** (will be installed during setup)

---

## Project Structure

```
Library Management System/
├── Backend SpringBoot/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/Library/Management/
│   │   │   │   ├── LibraryManagementSystemProjectApplication.java
│   │   │   │   ├── Config/
│   │   │   │   │   ├── DatabaseConfig.java
│   │   │   │   │   └── CorsConfig.java
│   │   │   │   ├── Controllers/
│   │   │   │   │   ├── BooksController.java
│   │   │   │   │   ├── UsersController.java
│   │   │   │   │   ├── BooksIssuedController.java
│   │   │   │   │   └── AuthorsController.java
│   │   │   │   ├── Services/
│   │   │   │   │   ├── BooksServices.java
│   │   │   │   │   ├── UserServices.java
│   │   │   │   │   └── BookIssuedServices.java
│   │   │   │   ├── DAO/
│   │   │   │   │   ├── BooksDAO.java
│   │   │   │   │   ├── UsersDAO.java
│   │   │   │   │   └── BookIssuedDAO.java
│   │   │   │   ├── Models/
│   │   │   │   │   ├── Books.java
│   │   │   │   │   ├── Users.java
│   │   │   │   │   ├── BookIssued.java
│   │   │   │   │   └── Request/
│   │   │   │   ├── Security/
│   │   │   │   │   └── SecurityConfig.java
│   │   │   │   └── Model/Response/
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   ├── pom.xml
│   └── mvnw
└── Frontend Angular/
    ├── src/
    │   ├── app/
    │   │   ├── app.ts
    │   │   ├── app.routes.ts
    │   │   ├── book-manager/
    │   │   │   ├── book-manager.ts
    │   │   │   ├── book-manager.html
    │   │   │   └── book-manager.css
    │   │   ├── models/
    │   │   ├── services/
    │   │   │   └── book.service.ts
    │   │   └── shared/
    │   ├── index.html
    │   ├── main.ts
    │   └── styles.css
    ├── package.json
    └── angular.json
```

---

## Database Setup

### Step 1: Create the Database

Before running the application, you need to set up the MySQL database. Run the following SQL script:

```sql
-- Database creation and setup script
-- Location: See database.sql file
```

**Important**: A complete `database.sql` file is provided in the project root. Execute it using:

```bash
mysql -u root -p < database.sql
```

Or manually execute the SQL commands in your MySQL client.

### Database Schema Overview

The system uses the following tables:

| Table | Purpose |
|-------|---------|
| `books` | Stores book information (name, description, type) |
| `authors` | Stores author information |
| `book_auth` | Junction table linking books and authors (many-to-many) |
| `users` | Stores user account information and credentials |
| `roles` | Stores user roles (Admin, Member) |
| `books_issued` | Tracks book issuance and return history |

---

## Installation & Setup

### Backend Setup

#### Step 1: Navigate to Backend Directory
```bash
cd "Backend SpringBoot"
```

#### Step 2: Configure Database Connection
Edit the file `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/librarymanagementsystem
spring.datasource.username=root
spring.datasource.password=your_mysql_password
```

**Update the following values**:
- `spring.datasource.password`: Replace `Afling@123` with your MySQL root password

#### Step 3: Build the Project
```bash
mvn clean install
```

#### Step 4: Run the Application
```bash
mvn spring-boot:run
```

The backend server will start on: **http://localhost:8080**

---

### Frontend Setup

#### Step 1: Navigate to Frontend Directory
```bash
cd "Frontend Angular"
```

#### Step 2: Install Dependencies
```bash
npm install
```

#### Step 3: Configure Backend API URL
If you need to modify the API base URL, update the service files in `src/app/services/`:

```typescript
// Default: http://localhost:8080/
// Update if your backend runs on a different port
```

#### Step 4: Run the Development Server
```bash
npm start
```

Or alternatively:
```bash
ng serve
```

The frontend application will be available at: **http://localhost:4200**

---

## API Endpoints

### Books API
- `GET /books` - Get all books with authors
- `POST /books/add` - Add a new book
- `PUT /books/{bookId}` - Update a book
- `DELETE /books/{id}` - Delete a book

### Users API
- `POST /users/add` - Register a new user
- `POST /users/login` - User login (email and password)

### Books Issued API
- `GET /books-issued` - Get all issued books
- `POST /books-issued/issue` - Issue a book to a user
- `PUT /books-issued/{issueId}/return` - Mark a book as returned

### Authors API
- `GET /authors` - Get all authors
- `POST /authors/add` - Add a new author

---

## Configuration Changes Required

### 1. Database Credentials
**File**: `Backend SpringBoot/src/main/resources/application.properties`

```properties
# Update these values with your database credentials
spring.datasource.username=root
spring.datasource.password=your_password_here
```

### 2. CORS Configuration
**File**: `Backend SpringBoot/src/main/java/com/Library/Management/Config/CorsConfig.java`

The backend is configured to accept requests from `http://localhost:4200` (Angular frontend). If your frontend runs on a different port, update the CORS configuration:

```java
// Modify the allowed origin
@CrossOrigin(origins = "http://localhost:your_port")
```

### 3. API Base URL (Frontend)
**File**: `Frontend Angular/src/app/services/book.service.ts`

Ensure the API URL matches your backend server:

```typescript
private apiUrl = 'http://localhost:8080/api';  // Update port if needed
```

### 4. Angular Configuration
**File**: `Frontend Angular/angular.json`

For production build with different backend URL, update the proxy configuration or environment files.

---

## Running the Application

### Development Mode

**Terminal 1 - Start Backend**:
```bash
cd "Backend SpringBoot"
mvn spring-boot:run
```

**Terminal 2 - Start Frontend**:
```bash
cd "Frontend Angular"
npm start
```

Access the application at: **http://localhost:4200**

### Production Build

#### Backend
```bash
cd "Backend SpringBoot"
mvn clean package
java -jar target/LibraryManagementSystemProject-0.0.1-SNAPSHOT.jar
```

#### Frontend
```bash
cd "Frontend Angular"
npm run build
# Deploy the generated dist/ folder to your web server
```

---

## User Roles & Permissions

### Admin Role
- Full access to all features
- Can manage books, authors, and users
- Can view all issued books and return history

### Member Role
- Can view available books
- Can borrow books (subject to library policies)
- Can view their own issued books and history

---

## Default Users

After running the database setup script, the following roles are pre-configured:

| Role | Description |
|------|-------------|
| Admin | Has full access to manage books, authors, and users |
| Member | Can borrow books and view issued history |

**Note**: Create user accounts using the registration endpoint or directly in the database.

---

## Troubleshooting

### Issue: Backend fails to start
**Solution**: 
- Verify MySQL server is running
- Check database credentials in `application.properties`
- Ensure the database `librarymanagementsystem` exists

### Issue: Frontend cannot connect to backend
**Solution**:
- Verify backend is running on http://localhost:8080
- Check CORS configuration in CorsConfig.java
- Ensure no firewall is blocking the connection
- Check browser console for error messages

### Issue: Port already in use
**Solution**:
- Backend: Change port in `application.properties`
  ```properties
  server.port=8081
  ```
- Frontend: Change port with
  ```bash
  ng serve --port 4201
  ```

### Issue: Maven build fails
**Solution**:
- Clear Maven cache: `mvn clean`
- Update Maven: `mvn -version`
- Ensure Java 21 is installed: `java -version`

---

## Project Dependencies

### Backend (Spring Boot)
- Spring Boot Starter Web
- Spring Boot Starter JDBC
- Spring Boot Starter Security
- Spring Boot DevTools
- MySQL Connector/J
- Spring Boot Starter Test

### Frontend (Angular)
- Angular Core Libraries
- Angular Material
- Bootstrap 5
- RxJS
- TypeScript

---

## Development Guidelines

### Backend Code Structure
1. **Controllers**: Handle HTTP requests and responses
2. **Services**: Contain business logic
3. **DAO**: Data access layer for database operations
4. **Models**: Entity classes representing database tables
5. **Config**: Configuration classes (Database, Security, CORS)

### Frontend Code Structure
1. **Components**: Reusable UI components
2. **Services**: API communication and business logic
3. **Models**: TypeScript interfaces and classes
4. **Shared**: Utility functions and helpers
5. **Assets**: Static resources (images, styles)

---

## Building & Deployment

### Docker Support (Optional)

For containerization, create a `Dockerfile` in each project directory.

### Continuous Integration
Configure your CI/CD pipeline (GitHub Actions, Jenkins, GitLab CI) to:
1. Run tests
2. Build the project
3. Deploy to your server

---

## Contributing

1. Create a feature branch
2. Make your changes
3. Commit with clear messages
4. Push to the repository
5. Create a pull request

---

## License

This project is part of the Library Management System initiative.

---

## Support & Contact

For issues, questions, or suggestions:
- Check the troubleshooting section
- Review the code comments
- Contact the development team

---

## Version History

| Version | Date | Changes |
|---------|------|---------|
| 0.0.1 | Nov 2025 | Initial release with core functionality |

---

## Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Angular Documentation](https://angular.io/docs)
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [Bootstrap Documentation](https://getbootstrap.com/docs/)

---

**Last Updated**: November 25, 2025

