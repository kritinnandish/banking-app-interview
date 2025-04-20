# Banking Application - Interview Exercise

This repository contains a Spring Boot-based banking application developed as part of an interview exercise. It provides basic functionalities for managing bank accounts and transactions, including retrieving account details, fetching transaction history, generating monthly statements, and creating new transactions. The application also includes a mock authentication mechanism for securing the API endpoints.

## Overview of Features

* **Account Management:**
    * Retrieve details of all accounts.
    * Retrieve details of a specific account by ID.
* **Transaction Management:**
    * Retrieve all transactions for an authenticated user.
    * Retrieve a specific transaction by ID.
    * Create new transactions (credit or debit).
    * Retrieve a monthly statement for a specific account.
* **Authentication:**
    * Uses a mock authentication controller to generate JWT tokens.
    * Secures API endpoints using Spring Security and role-based access control (`@PreAuthorize`).
* **API Documentation:**
    * Includes Swagger OpenAPI 3 documentation accessible at `/v3/api-docs/` and Swagger UI at `/swagger-ui/`.
* **Data Initialization:**
    * Uses a `MockDataInitializer` to populate the in-memory H2 database with sample account and transaction data on application startup.

## Technologies Used

* Java 17
* Spring Boot
* Spring Security (OAuth2 Resource Server)
* Spring Data JPA
* H2 Database (in-memory)
* Lombok (for code reduction)
* Swagger OpenAPI 3
* Maven

## Setup and Running the Application

1.  **Prerequisites:**
    * Java Development Kit (JDK) 17 or higher.
    * Maven.

2.  **Clone the Repository:**
    ```bash
    git clone <your_repository_url>
    cd banking-app-interview
    ```
    (Replace `<your_repository_url>` with the actual URL of your GitHub repository)

3.  **Build the Application:**
    ```bash
    ./mvnw clean install
    ```

4.  **Run the Application:**
    ```bash
    ./mvnw spring-boot:run
    ```
    The application will start on `http://localhost:8080`.

## API Endpoints

* **`POST /auth/token`**: Generates a mock JWT token for authentication.
* **`GET /accounts`**: Retrieves all accounts (requires authentication with `new_app_role`).
* **`GET /accounts/{id}`**: Retrieves an account by ID (requires authentication with `new_app_role`).
* **`GET /transactions`**: Retrieves all transactions (requires authentication with `new_app_role`).
* **`GET /transactions/{id}`**: Retrieves a transaction by ID (requires authentication with `new_app_role`).
* **`POST /transactions`**: Creates a new transaction (requires authentication with `new_app_role`).
    * **Request Body Example:**
        ```json
        {
          "transactionType": "debit",
          "amount": 50.00,
          "description": "Online Purchase",
          "date": "2025-04-22",
          "account": {
            "id": 1
          }
        }
        ```
* **`GET /transactions/statement/{accountId}/{year}/{month}`**: Retrieves the monthly statement for a given account, year, and month (requires authentication with `new_app_role`). Use the integer value for the month (e.g., 1 for January, 4 for April).
    * **Example:** `/transactions/statement/1/2025/4`

## Authentication

All API endpoints (except `/auth/token` and Swagger UI) are secured using JWT. To access them, you need to:

1.  Obtain a JWT by sending a `POST` request to `/auth/token`.
2.  Include the JWT in the `Authorization` header of your subsequent requests as a Bearer token:
    ```
    Authorization: Bearer <your_jwt_here>
    ```

## Demo

The application is initialized with mock data. You can use tools like `curl` or Postman to interact with the API endpoints. Screenshots of successful requests and responses for the key functionalities (get accounts, get transactions, create transaction, get monthly statement) should be included separately.

## Further Improvements (Optional)

* Implement a more realistic authentication mechanism.
* Add proper error handling and validation.
* Implement unit and integration tests.
* Consider using a persistent database instead of the in-memory H2 database.
* Add functionality for updating and deleting accounts/transactions.

## Author

Kritin Nandish

---

