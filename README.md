# 📓 Journaling App

A RESTful backend application built with **Spring Boot** and **MongoDB** that allows users to create, manage, and search through personal journal entries via a clean API interface.

---

## 🔍 About the Project

JournalingApp is a personal project built to practice designing and implementing a production-style REST API from scratch. It focuses on core backend principles — clean API design, NoSQL data modelling, and CRUD operations — using the Spring Boot ecosystem.

The app serves as a backend service that any frontend or mobile client can consume, making it highly extensible.

---

## ✨ Features

- 📝 **Create Entries** — Add new journal entries with title, content, and timestamp
- ✏️ **Edit Entries** — Update existing entries by ID
- 🗑️ **Delete Entries** — Remove entries permanently
- 📖 **View Entries** — Fetch a single entry or list all entries
- 🔍 **Search** — Search journal entries by keyword across titles or content

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java |
| Framework | Spring Boot |
| Database | MongoDB |
| API Style | REST |
| Authentication | Spring Security + JWT |
| Build Tool | Maven / Gradle |
| Testing | Postman |

---

## 🔐 Authentication

All endpoints are protected. The app uses **JWT-based authentication**.

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/auth/register` | Register a new user |
| `POST` | `/api/auth/login` | Login and receive a JWT token |

Include the token in the `Authorization` header for all subsequent requests:

```
Authorization: Bearer <your_token_here>
```

---

## 📡 API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/journals` | Fetch all journal entries |
| `GET` | `/api/journals/{id}` | Fetch a single entry by ID |
| `POST` | `/api/journals` | Create a new journal entry |
| `PUT` | `/api/journals/{id}` | Update an existing entry |
| `DELETE` | `/api/journals/{id}` | Delete an entry |
| `GET` | `/api/journals/search?q={keyword}` | Search entries by keyword |

---

## 🗂️ Sample Request & Response

**POST** `/api/journals`

```json
// Request Body
{
  "title": "My first entry",
  "content": "Today was a great day. I learned about Spring Boot and MongoDB."
}

// Response — 201 Created
{
  "id": "64f2a3c1e4b0c72d1f3a9b7e",
  "title": "My first entry",
  "content": "Today was a great day. I learned about Spring Boot and MongoDB.",
  "createdAt": "2024-09-01T10:30:00Z"
}
```

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven or Gradle
- MongoDB running locally or a [MongoDB Atlas](https://www.mongodb.com/cloud/atlas) cluster
- Postman (for testing the API)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/aishwarya14-dev/journalingApp.git
   cd journalingApp
   ```

2. **Configure MongoDB connection**

   Update `src/main/resources/application.properties`:
   ```properties
   spring.data.mongodb.uri=mongodb://localhost:27017/journalingdb
   ```
   Or set your Atlas connection string if using cloud.

3. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```
   The server starts at `http://localhost:8080`

4. **Test with Postman**
   - Import the endpoints listed above
   - Set `Content-Type: application/json` in headers for POST/PUT requests

---

## 📂 Project Structure

```
journalingApp/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/journalingApp/
│       │       ├── controller/       # REST Controllers
│       │       ├── service/          # Business logic
│       │       ├── repository/       # MongoDB repositories
│       │       └── model/            # Entity/Document classes
│       └── resources/
│           └── application.properties
└── pom.xml
```

---

## 🧠 What I Learned

- Designing RESTful APIs following standard HTTP conventions
- Integrating Spring Boot with MongoDB using Spring Data
- Working with MongoDB documents vs relational tables
- Structuring a Spring Boot project using layered architecture (Controller → Service → Repository)
- Implementing JWT-based authentication with Spring Security
- Implementing search functionality using MongoDB queries

---

## ⚠️ Known Limitations

- No frontend UI; the API is consumed and tested via Postman
- Search is basic keyword matching; full-text search indexing can be added as an enhancement

---

## 🔮 Future Enhancements

- Mood/tag tagging for entries
- Pagination for large entry lists
- Deploy to cloud (Railway, Render, or AWS)

---

## 👩‍💻 Author

**Aishwarya**
- GitHub: [@aishwarya14-dev](https://github.com/aishwarya14-dev)

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).
