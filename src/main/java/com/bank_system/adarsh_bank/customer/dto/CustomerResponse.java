package com.bank_system.adarsh_bank.customer.dto;

import com.bank_system.adarsh_bank.customer.KycStatus;

public class CustomerResponse {
    private Long id;
    private String name;
    private String email;
    private KycStatus kycStatus;

    public CustomerResponse(Long id, String name, String email, KycStatus kycStatus) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.kycStatus = kycStatus;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public KycStatus getKycStatus() { return kycStatus; }
}
