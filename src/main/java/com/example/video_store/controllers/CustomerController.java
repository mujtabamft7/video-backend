package com.example.video_store.controllers;

import com.example.video_store.models.Customer;
import com.example.video_store.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.registerCustomer(customer));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerByCustomerId(@PathVariable int customerId) {
        Optional<Customer> customer = customerService.getCustomerByCustomerId(customerId);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/login")
    public ResponseEntity<Customer> login(@RequestBody LoginRequest loginRequest) {
        Optional<Customer> customer = customerService.login(loginRequest.getEmail(), loginRequest.getPassword());
        return customer.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(403).build());
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomerByCustomerId(@PathVariable int customerId) {
        Optional<Customer> customer = customerService.getCustomerByCustomerId(customerId);
        if (customer.isPresent()) {
            customerService.deleteCustomer(customer.get().getId());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

class LoginRequest {
    private String email;
    private String password;

   
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
