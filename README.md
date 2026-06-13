# 📋 Task Manager — Full Stack Project
### Java Spring Boot + React.js | Built by Bhupesh Sahu

A full-stack task management application demonstrating REST API design,
JWT authentication, and a modern React frontend.

---

## 🛠️ Tech Stack

| Layer      | Technology                              |
|------------|-----------------------------------------|
| Backend    | Java 17, Spring Boot 3.2, Spring Security |
| Auth       | JWT (JSON Web Tokens)                   |
| Database   | MySQL + Spring Data JPA / Hibernate     |
| Build Tool | Maven                                   |
| Frontend   | React.js, Axios, Vite                   |

---

## 📁 Project Structure

```
taskmanager/
├── taskmanager-backend/
│   ├── pom.xml
│   └── src/main/java/com/bhupesh/taskmanager/
│       ├── TaskManagerApplication.java     ← Entry point
│       ├── model/
│       │   ├── User.java                   ← User entity
│       │   └── Task.java                   ← Task entity (TODO/IN_PROGRESS/DONE)
│       ├── dto/
│       │   ├── TaskDTO.java                ← Task data transfer object
│       │   └── AuthDTO.java                ← Login/Register/Response DTOs
│       ├── repository/
│       │   ├── UserRepository.java         ← JPA queries for User
│       │   └── TaskRepository.java         ← JPA queries for Task
│       ├── service/
│       │   ├── AuthService.java            ← Register & Login logic
│       │   └── TaskService.java            ← CRUD + business logic
│       ├── controller/
│       │   ├── AuthController.java         ← POST /api/auth/register, /login
│       │   └── TaskController.java         ← GET/POST/PUT/PATCH/DELETE /api/tasks
│       ├── security/
│       │   ├── JwtUtil.java                ← Token generate/validate/extract
│       │   └── JwtAuthFilter.java          ← Intercept & auth every request
│       ├── config/
│       │   └── SecurityConfig.java         ← Spring Security + CORS setup
│       └── exception/
│           └── GlobalExceptionHandler.java ← Centralized error handling
│
└── taskmanager-frontend/
    ├── package.json
    └── src/
        ├── App.jsx                         ← Root component + routing
        ├── context/
        │   └── AuthContext.jsx             ← Global auth state
        ├── pages/
        │   ├── Login.jsx                   ← Login + Register form
        │   └── Dashboard.jsx               ← Main task management UI
        └── services/
            └── api.js                      ← All Axios API calls
```

---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven 3.8+
- MySQL 8+
- Node.js 18+
- npm

---

### Backend Setup

1. **Create the database**
```sql
CREATE DATABASE taskmanager_db;
```

2. **Update credentials** in `src/main/resources/application.properties`:
```properties
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

3. **Run the backend**
```bash
cd taskmanager-backend
mvn spring-boot:run
```

Backend starts at: `http://localhost:8080`

---

### Frontend Setup

```bash
cd taskmanager-frontend
npm install
npm run dev
```

Frontend starts at: `http://localhost:5173`

---

## 🔌 API Endpoints

### Auth (Public)
| Method | Endpoint              | Description     |
|--------|-----------------------|-----------------|
| POST   | /api/auth/register    | Register user   |
| POST   | /api/auth/login       | Login, get JWT  |

### Tasks (JWT Required)
| Method | Endpoint                    | Description           |
|--------|-----------------------------|-----------------------|
| GET    | /api/tasks                  | Get all my tasks      |
| POST   | /api/tasks                  | Create a task         |
| PUT    | /api/tasks/{id}             | Update a task         |
| PATCH  | /api/tasks/{id}/status      | Update status only    |
| DELETE | /api/tasks/{id}             | Delete a task         |

---

## 🧪 Test with Postman

### 1. Register
```json
POST /api/auth/register
{
  "username": "bhupesh",
  "email": "bhupesh@example.com",
  "password": "password123"
}
```

### 2. Login → Copy the token
```json
POST /api/auth/login
{
  "email": "bhupesh@example.com",
  "password": "password123"
}
```

### 3. Create Task (add Bearer token in Authorization header)
```json
POST /api/tasks
Authorization: Bearer <your_token>
{
  "title": "Learn Spring Boot",
  "description": "Complete the REST API module",
  "priority": "HIGH",
  "status": "TODO",
  "dueDate": "2024-12-31T00:00:00"
}
```

---

## 💡 Key Concepts Demonstrated

- **OOP**: Model classes, Service layer, separation of concerns
- **REST API Design**: Proper HTTP methods, status codes, DTOs
- **Spring Security + JWT**: Stateless authentication
- **JPA / Hibernate**: Entity mapping, relationships (@OneToMany, @ManyToOne)
- **Spring Data JPA**: Custom query methods
- **Exception Handling**: @RestControllerAdvice
- **CORS Configuration**: Cross-origin setup for React frontend
- **React Hooks**: useState, useEffect, useContext
- **Axios**: HTTP client with interceptors for JWT

---

## 📈 Resume Bullet Points (copy these)

> Built a full-stack Task Manager application using Java Spring Boot (REST API,
> Spring Security, JWT authentication) with a React.js frontend, demonstrating
> end-to-end development with MySQL persistence and Hibernate ORM.

---

## 🔮 Future Improvements (mention in interviews)
- Add unit tests with JUnit & Mockito
- Task categories / labels
- Email notifications for due tasks
- Deploy backend on Render, frontend on Vercel
- Add Swagger/OpenAPI documentation
