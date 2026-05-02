# 02 - Implementing Concurrency Locks

Now that you've seen the "Damage" a race condition can cause ($130 missing/created!), let's learn how to fix it. There are two main strategies in the professional world.

## 1. Pessimistic Locking (The Guard)
**Analogy**: You lock the entire vault so only ONE person can enter the room. No one else can even LOOK at the balance until you leave.

### How to implement:
In your `AccountRepository`, you tell Spring Data JPA to lock the row in the database:

```java
@Lock(LockModeType.PESSIMISTIC_WRITE)
@Query("SELECT a FROM Account a WHERE a.id = :id")
Optional<Account> findByIdWithLock(Long id);
```

- **Pros**: Very safe. No "Race" possible.
- **Cons**: Slow. If 1000 people try to access the same account, they all form a long line. It can also cause "Deadlocks" (where Thread A waits for B, and B waits for A).

---

## 2. Optimistic Locking (The Version Check)
**Analogy**: Everyone can enter the vault and look at the balance. But each account has a **Version Number**. When you try to save your changes, the bank checks: *"Is the version number still the same as when you started?"*
- If yes, save succeeds and version becomes `v+1`.
- If no (someone else changed it), save fails!

### How to implement:
This is the **Industry Standard** for high-performance apps.

#### Step 1: Add the `@Version` field to your Entity
```java
@Entity
public class Account {
    @Id ...
    private double balance;

    @Version
    private Long version; // Hibernate manages this automatically!
}
```

#### Step 2: Handle the "Retry" in Service
When a collision happens, Spring throws an `ObjectOptimisticLockingFailureException`. You can simply try again (Retry) or tell the user "Please refresh".

- **Pros**: Extremely fast. No waiting in lines.
- **Cons**: You must handle the "Failure" and retry the logic.

---

## 3. Which one should we use?
In our Bank, **Optimistic Locking** is better because:
1. Most users don't try to withdraw money from the same account at the exact same millisecond.
2. It keeps the bank fast and responsive.

## The "Fix" Workflow:
1. Hibernate generates SQL like: `UPDATE account SET balance = 1010, version = 2 WHERE id = 1 AND version = 1`.
2. If another thread already updated it to `version = 2`, the `WHERE version = 1` will find **0 rows**.
3. Hibernate sees that 0 rows were updated and shouts: *"Hey! Someone changed this while I was working!"*

---

## Exercise:
If we add `@Version` to our `Account` entity and run the `run_lab_test.sh` script, will the total money be exactly $100,000? 
**Answer**: Yes! Because the "Bad" updates will simply fail instead of overwriting the good ones.
