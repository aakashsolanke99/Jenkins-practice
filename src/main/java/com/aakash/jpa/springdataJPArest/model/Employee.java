package com.aakash.jpa.springdataJPArest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;

    public Employee(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
