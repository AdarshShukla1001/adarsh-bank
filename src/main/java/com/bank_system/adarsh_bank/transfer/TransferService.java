package com.bank_system.adarsh_bank.transfer;

import com.bank_system.adarsh_bank.account.Account;
import com.bank_system.adarsh_bank.account.AccountRepository;
import com.bank_system.adarsh_bank.transaction.Transaction;
import com.bank_system.adarsh_bank.transaction.TransactionRepository;
import com.bank_system.adarsh_bank.transaction.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public void transferInternal(Long fromAccountId, Long toAccountId, double amount) {
        // 1. Fetch Accounts
        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("Source account not found"));
        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new RuntimeException("Destination account not found"));

        // 2. Validate Balance
        if (fromAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance in account: " + fromAccountId);
        }

        // 3. Update Balances
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        // 4. Save Updated Accounts
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        // 5. Log Transactions (Immutability)
        transactionRepository.save(new Transaction(amount, TransactionType.TRANSFER_OUT, fromAccount));
        transactionRepository.save(new Transaction(amount, TransactionType.TRANSFER_IN, toAccount));
        
        // IMPORTANT: If any step above fails, the @Transactional annotation 
        // ensures that ALL changes are rolled back. No money is lost!
    }
}
