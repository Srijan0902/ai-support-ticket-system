package com.srijan.supportticketsystem.service.impl;

import com.srijan.supportticketsystem.dto.CustomerRequest;
import com.srijan.supportticketsystem.dto.CustomerResponse;
import com.srijan.supportticketsystem.entity.Customer;
import com.srijan.supportticketsystem.repository.CustomerRepository;
import com.srijan.supportticketsystem.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest request) {

        Customer customer = Customer.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();

        Customer savedCustomer = customerRepository.save(customer);

        return CustomerResponse.builder()
                .id(savedCustomer.getId())
                .name(savedCustomer.getName())
                .email(savedCustomer.getEmail())
                .phone(savedCustomer.getPhone())
                .build();
    }
}