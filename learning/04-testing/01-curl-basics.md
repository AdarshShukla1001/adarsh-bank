# 01 - Testing your API with Curl

Since your API doesn't have a website (frontend) yet, we need a way to send it "orders". The most common tool for this is **Curl**.

## 1. What is Curl?
Curl is a command-line tool that allows you to send HTTP requests (GET, POST, etc.) directly from your terminal.

## 2. Your Test Scripts
I've created a set of ready-to-run scripts for you in the `tests/account/` folder.

### How to run them:
Open a **new terminal tab** (keep your server running in the first tab) and run:

```bash
./tests/account/create_account.sh
./tests/account/get_all_accounts.sh
```

## 3. The Commands Explained

### POST (Create)
```bash
curl -X POST http://localhost:8080/api/accounts \
-H "Content-Type: application/json" \
-d '{"accountHolderName": "Adarsh", "balance": 1000.0}'
```
- `-X POST`: Tells the server we want to CREATE data.
- `-H`: Sends a "Header" telling the server we are sending JSON.
- `-d`: The actual data (JSON).

### GET (Read)
```bash
curl -X GET http://localhost:8080/api/accounts
```
- Just a simple request to fetch data.

### PUT (Update)
```bash
curl -X PUT http://localhost:8080/api/accounts/1 \
-H "Content-Type: application/json" \
-d '{"accountHolderName": "Adarsh Updated", "balance": 2000.0}'
```

### DELETE (Delete)
```bash
curl -X DELETE http://localhost:8080/api/accounts/1
```

---

## Exercise:
Try creating an account, then use the `get_all_accounts.sh` script to verify it was saved in the database!
