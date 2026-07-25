package com.srijan.supportticketsystem.service;

import com.srijan.supportticketsystem.dto.CustomerRequest;
import com.srijan.supportticketsystem.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {
    List<CustomerResponse> getAllCustomers();
    CustomerResponse createCustomer(CustomerRequest request);
    CustomerResponse getCustomerById(Long id);
    CustomerResponse updateCustomer(Long id, CustomerRequest request);
    void deleteCustomer(Long id);
}