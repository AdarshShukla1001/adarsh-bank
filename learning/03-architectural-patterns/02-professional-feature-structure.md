# 02 - Professional Feature Structure

Top tech companies (like Google, Amazon, Netflix) don't just put everything in one folder. They use a more granular structure within each feature to ensure **Security**, **Scalability**, and **Clean Code**.

## 1. The DTO Pattern (Data Transfer Object)
**Crucial Concept**: Never expose your Database Entity (`Account.java`) directly to the internet. 
- Why? Because your database might have sensitive fields (like `internalId` or `secretNote`) that you don't want the user to see.
- Instead, we use **DTOs**. We have a `RequestDTO` for incoming data and a `ResponseDTO` for outgoing data.

## 2. The Professional Structure
Inside a feature folder (like `account`), we create sub-packages:

```
com.bank.app.account
  ├── Account.java (Entity - Database only)
  ├── AccountController.java (API entry)
  ├── AccountService.java (Business logic)
  ├── AccountRepository.java (DB access)
  ├── dto/ (Data Transfer Objects)
  │    ├── AccountRequest.java (What user sends)
  │    └── AccountResponse.java (What user sees)
  ├── mapper/ (The "Translator")
  │    └── AccountMapper.java (Converts Entity <-> DTO)
  └── exception/ (Feature-specific errors)
       └── AccountNotFoundException.java
```

## 3. Why this structure?
1. **Safety**: Changes to the database table don't break the API (and vice versa).
2. **Readability**: You know exactly where to find the "Translator" or the "Error" code.
3. **Collaboration**: One developer can work on the Mapper while another works on the Service.

---

## Exercise:
Look at our current `AccountController`. It returns the `Account` object directly. In the next step, we will change it to return an `AccountResponse` instead. What fields would you include in an `AccountResponse` that might be different from the database?
