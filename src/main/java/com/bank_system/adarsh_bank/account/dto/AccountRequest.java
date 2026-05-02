package com.bank_system.adarsh_bank.account.dto;

public class AccountRequest {
    private String accountNumber;
    private Long customerId;
    private double balance;

    public AccountRequest() {}

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}
