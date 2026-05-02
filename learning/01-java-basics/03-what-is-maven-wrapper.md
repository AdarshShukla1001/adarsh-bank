# 03 - What is the Maven Wrapper (`mvnw`)?

You might be wondering: "Why am I running `./mvnw` instead of just `java`?" 

## 1. What is Maven?
Before understanding the wrapper, you need to know **Maven**. Maven is a **Build Tool**. 
In the old days, if you wanted to use a library (like a JSON parser), you had to:
1. Download a `.jar` file from a website.
2. Put it in a folder.
3. Tell Java where that folder is.
4. If that library needed *another* library, you had to download that too!

**Maven automates this.** You just list the name of the library in `pom.xml`, and Maven downloads it and all its dependencies for you.

## 2. What is the "Wrapper" (`mvnw`)?
Normally, to use Maven, you have to install it on your computer. But what if:
- You have Maven version 3.6 installed, but the project needs 3.8?
- Your friend doesn't have Maven installed at all?

This is where the **Maven Wrapper** (`mvnw`) comes in. It is a small script included in your project that:
1. **Checks** if you have the correct version of Maven.
2. **Downloads** Maven automatically if you don't have it (it stores it in your `~/.m2` folder).
3. **Runs** the Maven commands using that specific version.

**It ensures that the project runs the EXACT same way on your computer, my computer, and the server.**

## 3. How the command works
When you run `./mvnw spring-boot:run`:
- `./mvnw`: "Run the wrapper script in the current directory."
- `spring-boot`: "Talk to the Spring Boot plugin."
- `:run`: "Execute the 'run' goal" (which compiles your code and starts the server).

### Other common commands:
- `./mvnw clean`: Deletes the `target` folder (good for a fresh start).
- `./mvnw compile`: Compiles your Java code into `.class` files.
- `./mvnw package`: Bundles your entire app into a single `.jar` file that you can deploy.
