package com.testAPI.demo.payload.response;

import lombok.Data;

import java.util.UUID;

@Data
public class EmployeeResponse {
    private UUID employeeId;
    private String firstname;
    private String lastname;
    private int age;
    private String companyName;
    private String companyCity;
}
