````markdown
# 🔗 URL Shortener

A simple yet scalable URL shortening service built with **Spring Boot**, inspired by [roadmap.sh URL Shortener Project](https://roadmap.sh/projects/url-shortening-service).

---

## 🧰 Tech Stack

- **Java 21**
- **Spring Boot 3**
- **Spring Data JPA**
- **H2 In-Memory Database**
- **Lombok**
- **Maven**
- **JUnit 5** (for testing)

---

## 🚀 Getting Started

### ✅ Prerequisites

- Java 21 installed
- Maven 3.6+ installed

### 📦 Clone & Build

```bash
git clone https://github.com/yourusername/url-shortener.git
cd url-shortener
mvn clean install
````

### ▶️ Run the Application

```bash
mvn spring-boot:run
```

* Server starts on: `http://localhost:8080`

---

## 🔌 API Endpoints & Usage

### 1. 📥 Create a Short URL

* **POST** `/api/shorten`
* **Request Body**

```json
{
  "url": "https://example.com/very/long/link"
}
```

* **Response**

```json
{
  "shortCode": "Xyz123",
  "url": "https://example.com/very/long/link",
  "createdAt": "2025-05-13T08:30:00"
}
```

---

### 2. 🔎 Get Original URL (No Redirect)

* **GET** `/api/shorten/{shortCode}`
* **Response**

```json
{
  "shortCode": "Xyz123",
  "url": "https://example.com/very/long/link",
  "createdAt": "2025-05-13T08:30:00"
}
```

---

### 3. 🔁 Redirect to Original URL

* **GET** `/api/{shortCode}`
* **Response:** HTTP `302 FOUND` with `Location` header pointing to the original URL

---

### 4. 🛠 Update Short URL

* **PUT** `/api/shorten/{shortCode}`
* **Request Body**

```json
{
  "url": "https://example.com/updated/link"
}
```

* **Response**

```json
{
  "shortCode": "Xyz123",
  "url": "https://example.com/updated/link",
  "updatedAt": "2025-05-13T09:00:00"
}
```

---

### 5. ❌ Delete Short URL

* **DELETE** `/api/shorten/{shortCode}`
* **Response:** `204 No Content` if deleted

---

### 6. 📊 Get URL Statistics

* **GET** `/api/shorten/{shortCode}/stats`
* **Response**

```json
{
  "shortCode": "Xyz123",
  "url": "https://example.com/very/long/link",
  "createdAt": "2025-05-13T08:30:00",
  "accessCount": 12
}
```

---

## 🧪 Testing the API

You can use **Postman**, **cURL**, or any API client.

Example using cURL:

```bash
curl -X POST -H "Content-Type: application/json" \
     -d '{"url":"https://example.com/long/url"}' \
     http://localhost:8080/api/shorten
```

---

## 💾 H2 Database Access

Access the H2 Console at: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

* **JDBC URL**: `jdbc:h2:mem:urlshortener`
* **Username**: `sa`
* **Password**: `password`

---

## ✅ Code Principles Followed

* ✅ **Clean Code**: Descriptive variable & method names
* ✅ **DRY Principle**: Utility methods for repetitive logic (e.g., error formatting)
* ✅ **Layered Architecture**:

  ```
  controller/
  service/
  repository/
  model/
  util/
  ```
* ✅ **Validation**: Input validation using JSR-380 (`@Valid`)
* ✅ **Exception Handling**: Proper status codes & responses

---

## 🛠️ Future Enhancements

| Feature                     | Description                                 |
| --------------------------- | ------------------------------------------- |
| 🐳 Docker Support           | Containerize the application for deployment |
| 🔁 Redis Caching            | Improve performance and rate-limiting       |
| 🔒 Rate Limiting & Auth     | Add user-level control and API keys         |
| 📈 Analytics Dashboard      | Add UI for access stats                     |
| 🧪 Unit + Integration Tests | Increase test coverage                      |
| 🌐 Swagger/OpenAPI Docs     | Auto-generate API documentation             |

---

## 🤝 Contributing

* Feel free to fork and raise pull requests
* All improvements are welcome!

---

## 📄 License

This project is licensed under the **MIT License**

---

## ✨ Author

Made with ❤️ by Abhishek Yadav

