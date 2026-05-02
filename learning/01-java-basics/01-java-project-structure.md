# 01 - Understanding Java Project Structure

Since you are looking at a Spring Boot project, the first thing to understand is where everything lives.

## 1. The Standard Maven Structure
Most Java projects (including yours) use **Maven**. Maven follows a standard directory layout:

- `src/main/java/`: This is where your **source code** lives. Everything inside here is what actually runs your application.
- `src/main/resources/`: This is for **non-code files** like configuration (`application.properties`), SQL scripts, or HTML templates.
- `src/test/java/`: This is where you write **unit tests** to make sure your code works correctly.
- `pom.xml`: The heart of your Maven project. It defines your project's dependencies (libraries you use), version, and build settings.

## 2. Spring Boot Specifics
In a Spring Boot app, you'll usually see these packages inside `src/main/java`:
- `controller`: Handles incoming web requests (REST endpoints).
- `service`: Contains the "business logic" (the actual calculations or rules).
- `repository`: Handles communication with the database.
- `entity` or `model`: Defines the structure of your data.

## 3. The `mvnw` file
You might have noticed `mvnw` and `mvnw.cmd`. These are **Maven Wrapper** files. They allow you to run Maven commands without having to install Maven manually on your computer.

### Exercise:
Look at your `src/main/java` folder. Find the file with `@SpringBootApplication` above the class name. That is the "Entry Point" of your entire application!
