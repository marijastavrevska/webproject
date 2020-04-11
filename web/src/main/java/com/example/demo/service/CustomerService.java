package com.example.demo.service;

import com.example.demo.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> listAllCustomers();

    Optional<Customer> getById(Long id);

    boolean findByUsername(String username);

    Customer saveOrUpdate(Customer customer);

    void delete(Long id);

}
