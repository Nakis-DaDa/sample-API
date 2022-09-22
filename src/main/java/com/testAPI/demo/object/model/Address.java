package com.testAPI.demo.object.model;

import lombok.Data;

import java.util.UUID;
@Data
public class Address {
    private UUID addressId;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
}
