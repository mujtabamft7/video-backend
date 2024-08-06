package com.example.video_store.services;

import com.example.video_store.models.Customer;
import com.example.video_store.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Customer registerCustomer(Customer customer) {
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        customer.setCustomerId(getNextCustomerId()); 
        return customerRepository.save(customer);
    }

    private int getNextCustomerId() {
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()) {
            return 1;
        } else {
            return customers.stream().mapToInt(Customer::getCustomerId).max().getAsInt() + 1;
        }
    }

    public Optional<Customer> getCustomerByCustomerId(int customerId) {
        return customerRepository.findByCustomerId(customerId);
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

    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }
}
