# 03 - Understanding JpaRepository

In most Java apps, you'd have to write long SQL queries like `INSERT INTO accounts ...` or `SELECT * FROM accounts WHERE id = ?`. **JpaRepository** does this for you automatically.

## 1. What is it?
`JpaRepository` is an interface provided by **Spring Data JPA**. When your repository interface extends it, Spring Boot generates the implementation code at runtime.

```java
public interface AccountRepository extends JpaRepository<Account, Long> {
}
```

## 2. What do you get for free?
Just by writing that one line, you get these methods ready to use:
- `save(S entity)`: Inserts a new row or updates an existing one.
- `findById(ID id)`: Finds a row by its Primary Key.
- `findAll()`: Gets every row in the table.
- `deleteById(ID id)`: Removes a row.
- `count()`: Tells you how many rows are in the table.

## 3. The Magic of "Query Methods"
This is the coolest part. You can add custom methods just by naming them correctly, and Spring will write the SQL!

*Example*: If you want to find an account by the holder's name:
```java
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByAccountHolderName(String name);
}
```
Spring sees `findBy` + `AccountHolderName` and automatically writes: 
`SELECT * FROM account WHERE account_holder_name = ?`

## 4. Why use it?
1. **No SQL Errors**: You don't have to worry about typos in your SQL strings.
2. **Database Independent**: If you switch from H2 to MySQL or PostgreSQL, you don't have to change your code—JpaRepository handles the differences for you.
3. **Speed**: You can build a full database layer in seconds.

---

## Exercise:
Go to your `AccountRepository.java`. Try adding a method called `findByBalanceGreaterThan(double amount)`. What SQL do you think Spring will generate for this?
