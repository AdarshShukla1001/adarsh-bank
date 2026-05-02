# 02 - Deep Dive: Spring Boot Folder Structure

When you download a project from **Spring Initializr** (start.spring.io), you get a standard layout. Here is the detailed breakdown:

## 1. The Root Directory
- **`pom.xml`**: This is the "Project Object Model". It's a configuration file for Maven. It lists every library (dependency) your project needs. If you want to add a database or a security layer, you add it here.
- **`mvnw` & `mvnw.cmd`**: These are the "Maven Wrappers".
  - `mvnw`: For macOS and Linux.
  - `mvnw.cmd`: For Windows.
  - They allow anyone to run the project without having to install Maven themselves.

## 2. The `src` Folder (Source)
This is where 99% of your work happens.
- **`src/main/java`**: Contains your Java source files.
  - Inside here, you'll find your **Base Package** (e.g., `com.example.bank`).
  - **`AdarshBankApplication.java`**: The entry point. It has the `@SpringBootApplication` annotation.
- **`src/main/resources`**: For non-Java files.
  - **`application.properties`**: This is where you configure things like your server port, database connection, or app name.
  - **`static/`**: Where you put CSS, Images, or JavaScript if you are building a frontend inside Spring.
  - **`templates/`**: Where HTML templates (like Thymeleaf) go.
- **`src/test/java`**: Where you write tests to ensure your code doesn't break.

## 3. The `target` Folder
You might see a `target/` folder after you run the project. This is **auto-generated**. It contains the compiled `.class` files and the final `.jar` file. You should never edit anything in here manually.

## 4. `.mvn` Folder
This contains the wrapper's own internal files. You can safely ignore this.

---

## Why did your command fail?
You tried: `.\mvnw spring-boot:run`

### The Problem:
1. **Backslash (`\`)**: In Windows, folders are separated by `\`. On **macOS**, folders are separated by `/`.
2. **The Dot-Slash (`./`)**: This tells the terminal to look for the file in the *current directory*.

### The Fix:
On macOS, you must use:
```bash
./mvnw spring-boot:run
```
Notice the **forward slash**!
