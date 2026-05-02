# 01 - Connecting Java Backend to Frontend

Until now, we've been using the terminal (`curl`) to talk to our bank. Now, we will build a **User Interface (UI)** so anyone can use it.

## 1. Separation of Concerns
In modern web development, we keep the "Brain" (Java) and the "Face" (HTML/CSS) separate. 
- **Backend (Java)**: Provides the data as JSON via REST APIs.
- **Frontend (JS)**: Fetches that JSON and displays it beautifully.

## 2. Where do static files go?
Spring Boot is pre-configured to serve files from the `src/main/resources/static` folder. 
- If you put `index.html` there, you can see it by visiting `http://localhost:8080/`.

## 3. How the Frontend talks to the Backend
We use a JavaScript function called `fetch()`. It works exactly like `curl` but inside the browser.

```javascript
// Example: Getting all accounts
fetch('/api/accounts')
  .then(response => response.json())
  .then(data => {
      console.log(data); // This is your list of accounts!
      // Now you can use JS to put this data into an HTML table
  });
```

## 4. Why this is the "Professional" way?
1. **Performance**: The browser only downloads the "Design" once. After that, it only downloads small bits of "Data" (JSON).
2. **Multi-Platform**: You can use the same Java backend for a Web App, an iPhone App, and an Android App.

---

## Exercise:
Look at your `index.html`. We will add a "Refresh" button. When clicked, it should call the `fetch()` function again. Why is this better than refreshing the whole page?
