package com.testAPI.demo.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Employee {
    private UUID id;
    private String firstname;
    private String lastname;
    private int age;

}
