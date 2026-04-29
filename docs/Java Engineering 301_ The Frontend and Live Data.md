# **Java Engineering 301: The Frontend and Live Data**

Now that your Java backend is serving data through an API, it's time to build the "Face" of the application. We will use the three core pillars of the web: **HTML** (Structure), **CSS** (Design), and **JavaScript** (Behavior).

## **Phase 1: The Structure (HTML)**

HTML uses "tags" to create a hierarchy. For a finance app, data is best shown in a **Table**. This keeps everything aligned and professional.

### **The Strategy:**

* Use \<table\> to start your data grid.  
* Use \<thead\> for the headers like "Symbol" and "Price".  
* **Crucial Step:** Create an empty \<tbody id="price-table-body"\>\</tbody\>. You give it an **ID** so JavaScript can find it later and "inject" the data.

## **Phase 2: Professional Styling (CSS)**

Without CSS, a table looks like a 1990s spreadsheet. Good design makes data easier to digest.

### **Financial UI Principles:**

| CSS Concept | Application   |
| :---- | :---- |
| **Text Alignment** | Always text-align: right; for numbers. This aligns decimal points. |
| **Zebra Striping** | Use tr:nth-child(even) to give every other row a light background. |
| **Visual Cues** | Create classes like .price-up { color: green; } for positive trends. |

## **Phase 3: Connecting the Dots (JavaScript)**

JavaScript is the "glue" that fetches data from your Java server and updates the table without refreshing the page. This is called **Asynchronous Communication**.

### **The Fetch-and-Render Loop:**

1. **Fetch:** Use fetch('http://localhost:8080/api/stocks/history') to get the JSON data.  
2. **Loop:** Use a forEach loop to go through every stock in that list.  
3. **Create Elements:** For each stock, create a new \<tr\> (table row) using backticks (\`\` \` \`\`) to insert the variables.  
4. **Append:** Add that row to your price-table-body.

## **Phase 4: Tracking Live Prices (Polling)**

A real ticker is "Live." We achieve this using a **Polling Loop**. This tells the browser to repeat your JavaScript fetch function every few seconds.

### **Your Task:**

* Use setInterval(fetchPrices, 5000);.  
* This means every 5 seconds, the browser will ask your Java server: "Any new data?" and the table will update automatically.

## **The "Pro" Debugging Tip**

In your browser, press **F12** to open the **Developer Tools**. Go to the **Network** tab. Here, you can see every single "conversation" your JavaScript has with your Java backend. If your table is empty, this tab will tell you exactly why (e.g., if the server is down or the URL is wrong).  
*By completing this, you have built a true Full-Stack application. You have a database for persistence, a Java API for logic, and a dynamic Web UI for the user.*