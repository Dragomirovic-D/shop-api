# SHOP API – Spring Boot REST API

## Description

This is a REST API simulating an online shop, implemented using Spring Boot.  
It covers basic functionalities for managing products and a shopping cart, uses Swagger for documentation, and Docker for containerization.

**Implemented Tasks:**
- Task 1: REST API functionality
- Task 3: Docker containerization

**Not Implemented:**
- Task 2: Authentication and security (JWT, role-based access)

---

## Technologies

- Java 21
- Spring Boot 3.5.4
- Spring Data JPA
- H2 database (in-memory)
- Maven
- Docker
- Swagger / OpenAPI
- JUnit

---

## Project Structure

- `controller` – REST API endpoints
- `service` – Business logic
- `repository` – Database interaction (JPA)
- `model` – Entity and DTO classes
- `exception` – Global error handlers
- `config` – CORS and Swagger configuration
- `resource` – JSON file with product data

---

## API Endpoints (Task 1)

GET /api/products  
-Returns all products

GET /api/products/{id}  
-Returns a single product by ID

POST /api/cart/add  
-Adds a product to the cart

GET /api/cart  
-Returns the cart contents

PUT /api/cart/item/{id}  
-Updates quantity of an item in the cart

DELETE /api/cart/item/{id}  
-Removes an item from the cart

---

## Swagger Documentation

Swagger UI available at:  
`http://localhost:8080/swagger-ui/index.html`

---

## Docker Support (Task 3)

The application is containerized using Docker.

**Build:**  
docker build -t shop-api .

**Run:**  
docker run -p 8080:8080 shop-api

---

## Tests

Basic unit tests are implemented for `ProductService` and `CartService`, as well as for controllers.

---

## Submission Requirements

### Code

- The project is hosted in a GitHub repository: [https://github.com/Dragomirovic-D/shop-api] 
- The repository includes a clear README file with a description of functionalities, technologies, and instructions for running the application.

---

### Documentation

#### Setup Instructions

1. Open the project in IntelliJ as a Maven project
2. Run the `ShopApiApplication.java` class
3. Open Swagger UI at:  
   `http://localhost:8080/swagger-ui/index.html`
4. Alternatively, use Docker:
   docker build -t shop-api .  
   docker run -p 8080:8080 shop-api

---

#### Architecture Decisions

- The application is structured using typical Spring layers (`controller`, `service`, `repository`, `model`, `exception`, `config`)
- Uses **Spring Data JPA** for data access
- Uses an **H2 in-memory database** for easy testing
- API documentation is provided via **Swagger/OpenAPI**
- The application is containerized using **Docker**

---

#### Self-Assessment

##### Challenges Faced and How You Solved Them

- **Time limitations:** Focused on REST API and containerization due to time constraints
- **JWT authentication:** Not implemented due to limited experience and time
- **Cart modeling:** Implemented without user accounts, only in memory
- **Testing:** Basic functions are covered, no advanced coverage

##### What You Would Improve with More Time

- Implementation of JWT authentication and authorization
- Persistent database for cart and product storage (e.g., PostgreSQL)
- More unit and integration testing
- Detailed Swagger documentation including edge cases
