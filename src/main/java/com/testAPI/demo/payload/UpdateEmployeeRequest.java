package com.testAPI.demo.payload;

import lombok.Data;

@Data
public class UpdateEmployeeRequest {
    private int id;
    private String firstname;
    private String lastname;
    private int age;
}
