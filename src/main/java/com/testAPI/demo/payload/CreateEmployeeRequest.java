package com.testAPI.demo.payload;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateEmployeeRequest implements Serializable {


    private String firstname;

    private String lastname;

    private int age;

}
