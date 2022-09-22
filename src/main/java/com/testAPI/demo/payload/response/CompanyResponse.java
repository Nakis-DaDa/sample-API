package com.testAPI.demo.payload.response;

import com.testAPI.demo.object.model.Address;
import lombok.Data;

import java.util.UUID;

@Data
public class CompanyResponse {
    private UUID companyId;
    private String companyName;
    private Address address;
}
