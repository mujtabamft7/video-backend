package com.example.video_store.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers")
@Data
public class Customer {
    @Id
    private String id;
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
