# 01 - Your First REST API Module

Now that your server is running, let's make it actually **do something**. We will create a simple REST API that says "Welcome to Adarsh Bank!".

## 1. What is a REST API?
Imagine you go to a restaurant. 
- You (the **Client**) look at the menu.
- You give an order to the waiter.
- The waiter (the **API**) takes your order to the kitchen.
- The kitchen (the **Server**) prepares the food.
- The waiter brings the food back to you.

A **REST API** is that waiter. It allows your browser or mobile app to "order" data from your Java server.

## 2. Key Annotations
To build an API in Spring Boot, we use two main "magic words" (Annotations):
- **`@RestController`**: Tells Spring Boot, "Hey, this class is a web controller. It will handle web requests."
- **`@GetMapping`**: Tells Spring Boot, "When someone visits this specific URL, run this method."

## 3. Step-by-Step Implementation

### Step 1: Create a Package
It's best practice to keep your controllers in their own folder. 
Create a new folder: `src/main/java/com/bank_system/adarsh_bank/controller`

### Step 2: Create the Controller Class
Create a file named `WelcomeController.java` inside that folder.

### Step 3: Add the Code
```java
package com.bank_system.adarsh_bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Adarsh Bank! Your server is up and running. 🚀";
    }
}
```

## 4. How to Test it
1. Stop your server (Ctrl+C in terminal).
2. Start it again: `./mvnw spring-boot:run`
3. Open your browser and go to: `http://localhost:8080/welcome`

---

## Exercise:
Try adding a second method called `healthCheck()` that returns `"Server Status: OK"` when you visit `http://localhost:8080/health`.
