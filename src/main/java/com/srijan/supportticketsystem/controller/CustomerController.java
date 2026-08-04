package com.srijan.supportticketsystem.controller;

import com.srijan.supportticketsystem.dto.CustomerRequest;
import com.srijan.supportticketsystem.dto.CustomerResponse;
import com.srijan.supportticketsystem.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerResponse createCustomer(@Valid @RequestBody CustomerRequest request) {
        return customerService.createCustomer(request);
    }
}