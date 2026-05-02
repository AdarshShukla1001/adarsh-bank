package com.bank_system.adarsh_bank.customer;

import com.bank_system.adarsh_bank.customer.dto.CustomerResponse;
import com.bank_system.adarsh_bank.customer.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private com.bank_system.adarsh_bank.notification.NotificationService notificationService;

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        Customer saved = customerRepository.save(customer);
        notificationService.sendGlobalUpdate("CUSTOMER_REGISTERED_SUCCESSFULLY");
        return saved;
    }

    @GetMapping
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerMapper::toResponse)
                .collect(Collectors.toList());
    }
}
