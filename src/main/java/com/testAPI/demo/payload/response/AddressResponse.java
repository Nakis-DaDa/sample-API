package com.testAPI.demo.payload.response;

import lombok.Data;
import java.util.UUID;

@Data
public class AddressResponse {
    private UUID addressId;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
}
