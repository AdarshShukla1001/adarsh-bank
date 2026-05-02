# 02 - Layered Architecture & CRUD APIs

In professional Spring Boot development, we don't put everything in one file. We use **Layered Architecture**. This keeps the code clean, organized, and easy to test.

## 1. The Four Layers
To build an "Account" system for our bank, we need 4 pieces:

1. **Entity (`model`)**: Represents the table in your database. 
   - *Example*: A class `Account` with fields like `id`, `accountHolderName`, and `balance`.
2. **Repository**: The "bridge" to the database. It handles all the SQL for you automatically.
3. **Service**: The "Brain". This is where business logic lives (e.g., "If I withdraw money, check if the balance is enough").
4. **Controller**: The "Face". It handles the HTTP requests (GET, POST, PUT, DELETE).

## 2. What is CRUD?
CRUD stands for:
- **C**reate (HTTP POST)
- **R**ead (HTTP GET)
- **U**pdate (HTTP PUT)
- **D**elete (HTTP DELETE)

## 3. The Plan
We are going to build a system to manage Bank Accounts. Here is the step-by-step:

### Step 1: Create the Entity
We'll create an `Account` class and use `@Entity` to tell Spring Boot to create a table in the H2 database.

### Step 2: Create the Repository
We'll create an interface that extends `JpaRepository`. This gives us methods like `save()`, `findAll()`, and `findById()` for free!

### Step 3: Create the Service
We'll create an `AccountService` to handle the logic.

### Step 4: Create the Controller
We'll create an `AccountController` to expose the endpoints.

---

## Exercise:
Before we write the code, look at your `pom.xml`. Can you find the `spring-boot-starter-data-jpa` dependency? That is the library that makes all of this possible!
