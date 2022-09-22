package com.testAPI.demo.object.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Employee {
    private UUID employeeId;
    private String firstname;
    private String lastname;
    private int age;
    private UUID companyId;

}
