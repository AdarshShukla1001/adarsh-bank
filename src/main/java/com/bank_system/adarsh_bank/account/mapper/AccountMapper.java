package com.bank_system.adarsh_bank.account.mapper;

import com.bank_system.adarsh_bank.account.Account;
import com.bank_system.adarsh_bank.account.dto.AccountRequest;
import com.bank_system.adarsh_bank.account.dto.AccountResponse;
import com.bank_system.adarsh_bank.customer.Customer;

public class AccountMapper {

    public static Account toEntity(AccountRequest request, Customer customer) {
        return new Account(
            request.getAccountNumber(),
            request.getBalance(),
            customer
        );
    }

    public static AccountResponse toResponse(Account account) {
        return new AccountResponse(
            account.getId(),
            account.getAccountNumber(),
            account.getCustomer().getId(),
            account.getBalance()
        );
    }
}
