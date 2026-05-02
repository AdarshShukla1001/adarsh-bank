# Learning Instructions for Antigravity

This file guides how Antigravity should handle learning-related requests for this project.

## Workflow for New Learnings
Whenever the user wants to learn a new concept or asks for an explanation of a Java/Spring Boot feature:
1. **Directory Selection**: Use the `learning/` directory.
2. **Naming Convention**: 
   - Folders should be prefixed with numbers for sequence (e.g., `01-java-basics`, `02-spring-boot-core`).
   - Files should be descriptive and numbered if they form a sequence.
3. **Content Structure**:
   - **Concept**: Clear explanation of the topic.
   - **Code Example**: Practical Java/Spring Boot code snippet.
   - **How it works**: Deep dive into the internal mechanics.
   - **Exercise**: A small task for the user to try.
4. **Integration**: If the learning is directly applicable to the `adarsh-bank` project, explain how it fits into the current architecture.

## Architecture & Structure Focus
- Focus on explaining the "Why" behind Spring Boot's folder structure (`src/main/java`, `src/main/resources`, etc.).
- Explain Maven/Gradle dependency management.
- Explain the Layered Architecture (Controller -> Service -> Repository -> Entity).
