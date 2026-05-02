package com.bank_system.adarsh_bank.account;

import com.bank_system.adarsh_bank.account.dto.AccountRequest;
import com.bank_system.adarsh_bank.account.dto.AccountResponse;
import com.bank_system.adarsh_bank.account.exception.AccountNotFoundException;
import com.bank_system.adarsh_bank.account.mapper.AccountMapper;
import com.bank_system.adarsh_bank.customer.Customer;
import com.bank_system.adarsh_bank.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private com.bank_system.adarsh_bank.notification.NotificationService notificationService;

    public AccountResponse createAccount(AccountRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + request.getCustomerId()));
        
        Account account = AccountMapper.toEntity(request, customer);
        Account savedAccount = accountRepository.save(account);
        
        notificationService.sendGlobalUpdate("NEW_ACCOUNT_CREATED");
        
        return AccountMapper.toResponse(savedAccount);
    }

    public List<AccountResponse> getAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(AccountMapper::toResponse)
                .collect(Collectors.toList());
    }

    public AccountResponse getAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + id));
        return AccountMapper.toResponse(account);
    }

    public AccountResponse updateAccount(Long id, AccountRequest request) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + id));
        
        account.setAccountNumber(request.getAccountNumber());
        account.setBalance(request.getBalance());
        
        Account updatedAccount = accountRepository.save(account);
        return AccountMapper.toResponse(updatedAccount);
    }

    public void deleteAccount(Long id) {
        if (!accountRepository.existsById(id)) {
            throw new AccountNotFoundException("Cannot delete. Account not found with id: " + id);
        }
        accountRepository.deleteById(id);
    }
}
