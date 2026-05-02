package com.bank_system.adarsh_bank.lab;

import com.bank_system.adarsh_bank.account.Account;
import com.bank_system.adarsh_bank.account.AccountRepository;
import com.bank_system.adarsh_bank.customer.Customer;
import com.bank_system.adarsh_bank.customer.CustomerRepository;
import com.bank_system.adarsh_bank.transfer.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/lab")
public class ConcurrencyLabController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransferService transferService;

    @PostMapping("/setup")
    public String setup() {
        // 1. Clear everything
        accountRepository.deleteAll();
        customerRepository.deleteAll();

        // 2. Create 100 Customers & Accounts
        for (int i = 1; i <= 100; i++) {
            Customer c = customerRepository.save(new Customer("User " + i, "user"+i+"@bank.com", "PAN"+i));
            accountRepository.save(new Account("ACC-" + i, 1000.0, c));
        }
        return "Setup Complete: 100 accounts created with $1000 each. Total Bank Money: $100,000";
    }

    @GetMapping("/total-money")
    public String getTotalMoney() {
        double total = accountRepository.findAll().stream()
                .mapToDouble(Account::getBalance)
                .sum();
        return "Current Total Money in Bank: $" + total;
    }

    @PostMapping("/run-race")
    public String runRaceCondition() throws InterruptedException {
        // We will perform 5,000 random transfers across all 100 accounts
        // using 100 parallel threads.
        
        ExecutorService executor = Executors.newFixedThreadPool(100); 
        List<Account> accounts = accountRepository.findAll();
        int accountCount = accounts.size();

        for (int i = 0; i < 5000; i++) {
            executor.submit(() -> {
                try {
                    // Pick two random accounts
                    int fromIdx = (int) (Math.random() * accountCount);
                    int toIdx = (int) (Math.random() * accountCount);
                    
                    if (fromIdx != toIdx) {
                        Long id1 = accounts.get(fromIdx).getId();
                        Long id2 = accounts.get(toIdx).getId();
                        transferService.transferInternal(id1, id2, 5.0);
                    }
                } catch (Exception e) {
                    // Errors like "Insufficient balance" are expected during stress test
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(2, TimeUnit.MINUTES);

        return "Rigorous Simulation Finished. Check /api/lab/total-money to see the massive damage!";
    }
}
