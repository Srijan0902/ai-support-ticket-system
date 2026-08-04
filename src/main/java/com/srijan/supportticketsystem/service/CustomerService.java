package com.srijan.supportticketsystem.service;

import com.srijan.supportticketsystem.dto.CustomerRequest;
import com.srijan.supportticketsystem.dto.CustomerResponse;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest request);

}