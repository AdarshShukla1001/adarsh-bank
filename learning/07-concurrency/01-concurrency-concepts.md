# 01 - Understanding Concurrency in Java

In a simple world, your code runs one line at a time. But in a real bank, **thousands of things happen at the same time**. Concurrency is the art of doing multiple tasks simultaneously without breaking everything.

## 1. The Analogy: The Bank Tellers
Imagine a bank with only **one teller**. 
- If 100 people arrive, person #100 has to wait for 99 people. This is **Single-Threaded**.
- Now imagine the bank has **10 tellers**. They can help 10 people at once. This is **Multi-Threaded (Concurrency)**.

## 2. The Nightmare: The Race Condition
Concurrency is powerful, but it has a dangerous side effect. Imagine two tellers (Threads) trying to help a husband and wife who are withdrawing $100 from the same account with only $150 balance.

1. **Thread A (Husband)**: Reads balance ($150).
2. **Thread B (Wife)**: Reads balance ($150).
3. **Thread A**: Withdraws $100, sets balance to $50.
4. **Thread B**: Withdraws $100... it thinks there is $150, so it succeeds! Sets balance to $50.
5. **Result**: $200 was withdrawn, but the balance is still $50. The bank just lost $150!

This is a **Race Condition**.

## 3. How Java Solves This
Java provides several tools to manage these "Race Conditions":

### A. The `synchronized` Keyword
This is like a **Lock** on the vault door. If Thread A is inside, Thread B must wait outside until A is finished.
```java
public synchronized void withdraw(double amount) {
    // Only ONE thread can enter here at a time
}
```

### B. Atomic Variables
Special variables (like `AtomicInteger`) that handle the "Read-Modify-Write" cycle in a single, un-interruptible step.

### C. Database Locking (The Professional Way)
In a real Spring Boot app, we usually handle concurrency at the **Database level**:
- **Pessimistic Locking**: "Lock the row in the DB so no one else can even read it until I'm done."
- **Optimistic Locking**: "Don't lock anything, but add a `@Version` column. If someone else changed the data while I was working, my save will fail, and I'll try again."

## 4. Why use Concurrency in our Bank?
1. **Performance**: Handle 1000 transfers per second instead of 10.
2. **Background Tasks**: Send emails or generate statements without making the user wait.
3. **External APIs**: Talk to other banks without freezing our own dashboard.

---

## Exercise:
If we use `synchronized` on our `transferMoney` method, what happens if 1000 people try to send money to 1000 different people at the same time? Is it efficient?
