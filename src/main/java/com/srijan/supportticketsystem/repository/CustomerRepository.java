package com.srijan.supportticketsystem.repository;

import com.srijan.supportticketsystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}