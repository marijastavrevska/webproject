package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository=customerRepository;
    }

    @Override
    public List<Customer> listAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }

    @Override
    public Optional<Customer> getById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public boolean findByUsername(String username) {
        return true;
    }

    @Override
    public Customer saveOrUpdate(Customer customer) {
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }
}
