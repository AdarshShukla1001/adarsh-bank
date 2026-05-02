# 04 - Transaction Atomicity with `@Transactional`

In a bank, "almost" isn't good enough. If you transfer $100, we cannot have a situation where the money is deducted from you but never reaches the recipient. 

## 1. The Concept of Atomicity
**Atomicity** (the 'A' in ACID) means that a series of database operations are treated as a **single unit**.
- Either **everything** succeeds.
- Or **everything** fails (and the database rolls back to its original state).

## 2. The Spring Solution: `@Transactional`
In Spring Boot, we use the `@Transactional` annotation on a method to tell Spring: *"Wrap this entire method in a database transaction."*

```java
@Transactional
public void transferMoney(Long fromId, Long toId, double amount) {
    // 1. Debit from Account A
    accountRepository.debit(fromId, amount);
    
    // 2. Credit to Account B
    accountRepository.credit(toId, amount);
    
    // 3. Log the Transaction
    transactionRepository.save(new Transaction(...));
    
    // If ANY of these fail (e.g. an exception is thrown), 
    // Spring will automatically undo (ROLLBACK) everything.
}
```

## 3. How it Works (Under the Hood)
1. **Begin**: When the method starts, Spring tells the database to "Start a Transaction".
2. **Execute**: The SQL commands are sent to the database, but they are "private" to this transaction.
3. **Commit**: If the method finishes without errors, Spring tells the database to "Commit" (make changes permanent).
4. **Rollback**: If an exception occurs, Spring tells the database to "Rollback" (delete the temporary changes).

## 4. Common Pitfalls
- **Checked Exceptions**: By default, `@Transactional` only rolls back for `RuntimeException` (like `NullPointerException`). If you use a checked exception, you must specify `@Transactional(rollbackFor = Exception.class)`.
- **Self-Invocation**: If you call a `@Transactional` method from another method in the *same class*, it might not work! (This is due to Spring's Proxy mechanism).

---

## Exercise:
Imagine you have a bug in Step 3 (Logging). If Step 1 and 2 succeeded but Step 3 crashed, what happens to the money in the database?
