package com.testAPI.demo.object.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Company {
    private UUID companyId;
    private String companyName;
    private UUID addressId;
    private Address address;
}
