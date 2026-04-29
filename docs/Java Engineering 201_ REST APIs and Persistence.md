# **Java Engineering 201: From Console to API with Persistence**

In the previous guide, you built a working system that ran in your console. Now, we are going to transform that into a **RESTful Web Service**. This is how real-world applications like Spotify, Amazon, and Instagram communicate with their users and store data.

## **Phase 1: The Web Framework (Spring Boot)**

A "Server" is just a program that waits for "Requests" over a network. Instead of building everything from scratch, we use a framework like **Spring Boot**. It handles the "plumbing" so you can focus on the logic.

### **The Concept: The Request-Response Cycle**

* **Request:** A user (or another app) sends a message saying "Give me the price of AAPL."  
* **Response:** Your server processes the request and sends back a JSON (data) object.

## **Phase 2: The Resource Layer (The API)**

The **Resource Layer** (also called the Controller Layer) is the front door of your application. Its only job is to receive HTTP requests and hand them off to the Service layer.

### **Your Task:**

* Create a package named com.ticker.controller.  
* Create a class StockController.  
* Annotate it with @RestController.  
* Create a method public List\<Stock\> getHistory().  
* **SOLID Tip:** This layer should not do math or fetch data from a DB. It should just call your TickerService.

## **Phase 3: The Persistence Layer (The Database)**

Currently, when you stop your app, all data is lost. We need a **Persistence Layer**. We will use an **In-Memory Database (H2)**. It acts like a real database but lives in your computer's RAM for easy development.

### **Your Task: The Repository (DAO)**

* Create a package named com.ticker.repository.  
* Create an interface StockRepository.  
* This layer is responsible for **CRUD** operations: Create, Read, Update, Delete.  
* Every time your Service fetches a new price, it should "Save" it to this repository.

## **Phase 4: Organizing the Flow (Layered Architecture)**

Now your application has a clear flow of data:

| Layer | Responsibility   |
| :---- | :---- |
| **Resource (Controller)** | Handles HTTP traffic (GET, POST). |
| **Service** | The "Brain" — coordinates between the Client and the DB. |
| **Repository (DAO)** | Talks to the Database. |

## **Phase 5: Testing with Postman**

You can't test a server using a console. You need **Postman**, which is a tool for sending professional API requests.

### **Your Task:**

1. Open Postman and create a new request.  
2. Set the method to GET.  
3. Enter the URL: http://localhost:8080/api/stocks/history.  
4. Click **Send** and look for the JSON array of stock prices you saved.

## **The "Level Up" Challenges**

* **Path Variables:** Change your API so the user can type /stocks/AAPL to get just one stock's history.  
* **The DTO Concept:** Learn why professionals use "Data Transfer Objects" instead of sending their database models directly to the UI.

*By separating these layers, you’ve built a system that is robust, testable, and ready for a professional environment.*