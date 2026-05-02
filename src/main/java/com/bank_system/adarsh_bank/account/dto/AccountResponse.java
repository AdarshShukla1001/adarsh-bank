package com.bank_system.adarsh_bank.account.dto;

public class AccountResponse {
    private Long id;
    private String accountNumber;
    private Long customerId;
    private double balance;

    public AccountResponse() {}

    public AccountResponse(Long id, String accountNumber, Long customerId, double balance) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.balance = balance;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}
