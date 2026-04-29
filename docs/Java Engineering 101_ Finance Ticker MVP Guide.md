# **Java Engineering 101: Building your first Finance Ticker MVP**

Welcome to the world of software engineering. This guide is designed to take you from writing simple scripts to building a structured, real-world Java application. We aren't just writing code; we are building a **system**.

## **Core Concepts to Master**

Before touching the keyboard, understand these two pillars of professional software:

| Concept | Definition in Plain English   |
| :---- | :---- |
| **Encapsulation (OOPS)** | Hiding the internal state of an object and requiring all interaction to be performed through public methods. Think of it like a remote control: you press buttons, but you don't touch the wires inside. |
| **Single Responsibility (SOLID)** | A class should have one, and only one, reason to change. A "Data Fetcher" should only fetch data; it shouldn't worry about how to print it. |

## **Phase 1: The Blueprint (The Model Layer)**

In Java, everything is an **Object**. If we are building a Finance Ticker, our core object is a Stock.

### **Your Task:**

* Create a package named com.ticker.model.  
* Create a class named Stock.  
* Add private fields: String symbol, double price, and String companyName.  
* Generate **Getters and Setters** for these fields.

**Why?** This is your "Data Layer." By making fields private, you prevent other parts of the app from accidentally breaking the data. This is **Encapsulation**.

## **Phase 2: The Messenger (The Client Layer)**

A "Client" is a specialized worker that talks to the outside world. In the beginning, we won't use a real API—we will use a **Mock Client**.

### **Your Task:**

* Create a package named com.ticker.client.  
* Create a class named FinanceClient.  
* Create a method public Stock fetchStock(String symbol).  
* **The Twist:** Instead of calling the internet, write code that manually creates and returns a new Stock(...) with hardcoded values.

**Why?** This allows you to build the rest of the app without waiting for an internet connection or API keys. You are "decoupling" your code.

## **Phase 3: The Brain (The Service Layer)**

The Service Layer is where the "logic" lives. If you want to check if a stock is a "Buy" or a "Sell," it happens here.

### **Your Task:**

* Create a package named com.ticker.service.  
* Create a class named TickerService.  
* This class needs to "know" about the FinanceClient. Create a constructor that takes the client as a parameter.  
* Create a method public void displayStockInfo(String symbol) that calls the client, gets the stock, and prints a beautiful summary to the console.

**Why?** This introduces **Dependency Injection**. The Service doesn't create the Client; it is *given* the Client. This makes your code very easy to test.

## **Phase 4: The Heartbeat (The Main Runner)**

The Main method is the glue. Its only job is to instantiate the classes and start the flow.

### **The Final Wiring:**

1. Initialize your FinanceClient.  
2. Initialize your TickerService, passing in the client.  
3. Use a Scanner to ask the user for a stock symbol (e.g., "AAPL").  
4. Call the service method and watch your MVP come to life\!

## **The "Level Up" Challenges**

Once the MVP is running with "Mock" data, try these to become a pro:

* **Logic Step:** In the Service layer, add logic to print the price in different colors if it is above or below a certain threshold.  
* **The Real Deal:** Research the java.net.http.HttpClient. Try to replace your hardcoded return in FinanceClient with a real call to a free API like Alpha Vantage or Yahoo Finance.

*Remember: Engineering is not about knowing every command; it's about knowing where each piece of the puzzle belongs.*