package com.bank_system.adarsh_bank.customer.mapper;

import com.bank_system.adarsh_bank.customer.Customer;
import com.bank_system.adarsh_bank.customer.dto.CustomerResponse;

public class CustomerMapper {

    public static CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(
            customer.getId(),
            customer.getName(),
            customer.getEmail(),
            customer.getKycStatus()
        );
    }
}
