package com.testAPI.demo.payload.request;

import com.testAPI.demo.object.model.Address;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CreateCompanyRequest {
    @NotNull(message ="company name cannot be null")
    @Size(min = 1, max = 45, message = "company name must be between 1 and 45 characters")
    private String companyName;
    private Address address;

}
