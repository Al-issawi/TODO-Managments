package com.gestion.demo.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;



//@Getter
//@Setter
@Entity
public class User {

    // Primary key with auto-increment strategy
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Field must not be blank, with validation message
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Username cannot be empty")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    // Embedded Address entity
    @Embedded
    private Address address;

    // Constructors, getters, and setters

    // Default constructor
    public User() {}

    // Parameterized constructor for convenience
    public User(Long id, String name, String username, String password, Address address) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.address = address;
    }

    //Getters And Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
