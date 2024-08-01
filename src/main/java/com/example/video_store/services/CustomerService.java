package com.example.video_store.services;

import com.example.video_store.models.Customer;
import com.example.video_store.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private AtomicInteger customerIdCounter = new AtomicInteger(1);

    public Customer registerCustomer(Customer customer) {
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        if (customer.getCustomerId() == 0) {
            customer.setCustomerId(1); // Set to 1 if customerId is not provided or is 0
        }
        return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomerById(String id) {
        return customerRepository.findById(id);
    }

    public Optional<Customer> login(String email, String password) {
        Optional<Customer> customer = customerRepository.findByEmail(email);
        if (customer.isPresent() && bCryptPasswordEncoder.matches(password, customer.get().getPassword())) {
            return customer;
        }
        return Optional.empty();
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerByCustomerId(int customerId) {
        return customerRepository.findByCustomerId(customerId);
    }

    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }
}
