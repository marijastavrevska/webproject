package com.example.demo.web;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

     @GetMapping("/users")
     public String addUsers() {
         Customer customer = new Customer(0, "Marija", "123456", "marija@gmail.com");
         customerRepository.save(new Customer(customer.getId(),customer.getName(), customer.getPassword(), customer.getEmail()));

         Customer customer1 = new Customer(0, "Elena", "111111", "elena@gmail.com");
         customerRepository.save(new Customer(customer1.getId(),customer1.getName(), customer1.getPassword(), customer1.getEmail()));

         Customer customer3 = new Customer(0, "Andrej", "123456", "andrej@gmail.com");
         customerRepository.save(new Customer(customer3.getId(),customer3.getName(), customer3.getPassword(), customer3.getEmail()));

         return "redirect:/";
     }

}
