# 01 - Package by Feature vs. Package by Layer

You noticed something important! As an app grows, putting all controllers in one folder and all services in another becomes a mess.

## 1. Package by Layer (What we had)
This is the "old school" way:
```
com.bank.app
  ├── controller
  │     ├── AccountController
  │     └── TransactionController
  ├── service
  │     ├── AccountService
  │     └── TransactionService
  └── repository
        ├── AccountRepository
        └── TransactionRepository
```
**The Problem**: If you want to change how "Accounts" work, you have to jump between 4 different folders. It's like keeping your socks in the bedroom, your shoes in the kitchen, and your pants in the garage!

## 2. Package by Feature (The better way)
This is how modern, modular apps are built:
```
com.bank.app
  ├── accounts
  │     ├── Account (Entity)
  │     ├── AccountController
  │     ├── AccountRepository
  │     └── AccountService
  └── transactions
        ├── Transaction (Entity)
        ├── TransactionController
        ├── TransactionRepository
        └── TransactionService
```
**The Benefits**:
- **Encapsulation**: You can keep certain classes "package-private" (so they can't be used outside that feature).
- **Modularity**: If you want to delete a feature, you just delete one folder.
- **Developer Experience**: Everything related to "Accounts" is in one place.

## 3. Our Refactoring Plan
We will move all "Account" related code into a single package: `com.bank_system.adarsh_bank.account`.

---

## Exercise:
Think about a "User Profile" feature. What files would you put inside a `user` package if we follow the "Package by Feature" rule?
