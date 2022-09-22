package com.testAPI.demo.payload.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CreateAddressRequest {
    @NotNull(message ="street address cannot be null")
    @Size(min = 1, max = 45, message = "street address must be between 1 and 45 characters")
    private String streetAddress;
    @NotNull(message ="city cannot be null")
    @Size(min = 1, max = 45, message = "city must be between 1 and 45 characters")
    private String city;
    @Size(max = 45, message = "state must be between 1 and 45 characters")
    private String state;
    @NotNull(message ="zip code cannot be null")
    @Size(min = 5, max = 5, message = "zip code must be 5 characters")
    private String zipCode;
}
