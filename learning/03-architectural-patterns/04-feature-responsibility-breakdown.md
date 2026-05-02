# 04 - Responsibility Breakdown (Account Module)

In a professional "Package by Feature" structure, every file has a **Single Responsibility**. If you know who is responsible for what, you will never get lost in the code.

## 📦 The `account` Feature Folder
This folder is the "Home" for all banking account logic.

| File / Folder | Responsibility | Real-world Analogy |
| :--- | :--- | :--- |
| **`Account.java`** (Entity) | **Database Model**: Maps Java code to the database table. | The Physical Vault (where the raw money is kept). |
| **`AccountRepository`** | **Data Access**: Executes SQL queries (save, find, delete). | The Librarian (who knows exactly where each vault is). |
| **`AccountService`** | **Business Logic**: The "Brain". Validates data and applies rules. | The Bank Manager (who decides if you're allowed to open an account). |
| **`AccountController`** | **API Entry**: Handles HTTP requests (URL, JSON). | The Bank Teller (the person you talk to at the counter). |
| **`dto/AccountRequest`** | **Input Filter**: Defines what the user *must* send to us. | The Account Opening Form (what you fill out). |
| **`dto/AccountResponse`** | **Output Filter**: Defines what the user *is allowed* to see. | The Account Statement (what the bank shows you). |
| **`mapper/AccountMapper`** | **Translation**: Converts DTOs <-> Entities. | The Translator (who turns your form into bank records). |
| **`exception/`** | **Error Handling**: Specific errors for this feature. | The "Transaction Denied" sign. |

## 🛠 Why this "Separation" matters?

### 1. The Security Layer (DTOs)
If you add a field `internalCreditScore` to `Account.java`, you can simply **not** add it to `AccountResponse`. The user will never see it. The Response DTO is your privacy shield.

### 2. The Logic Layer (Service)
If the bank decides that no one can open an account with less than $500, you only change **one file**: `AccountService.java`. The database doesn't care, and the Controller doesn't care.

### 3. The Database Layer (Repository)
If you move from an H2 database to a massive Oracle database, you might only need to tweak the **Repository**. The rest of your app stays exactly the same.

---

## Exercise:
If we want to add a feature that sends an email whenever an account is created, which file should we modify? (Hint: It's the "Brain").
