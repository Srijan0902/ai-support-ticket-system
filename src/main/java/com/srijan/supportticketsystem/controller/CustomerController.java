package com.srijan.supportticketsystem.controller;

import com.srijan.supportticketsystem.dto.CustomerRequest;
import com.srijan.supportticketsystem.dto.CustomerResponse;
import com.srijan.supportticketsystem.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping
    public List<CustomerResponse> getAllCustomers() {
        return customerService.getAllCustomers();
    }
    @GetMapping("/{id}")
    public CustomerResponse getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }
    @PutMapping("/{id}")
    public CustomerResponse updateCustomer(@PathVariable Long id,
                                           @Valid @RequestBody CustomerRequest request) {

        return customerService.updateCustomer(id, request);
    }
    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id) {

        customerService.deleteCustomer(id);

        return "Customer deleted successfully";
    }
}