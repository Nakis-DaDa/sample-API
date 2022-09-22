package com.testAPI.demo.service;

import com.testAPI.demo.payload.request.CreateCompanyRequest;
import com.testAPI.demo.payload.response.CompanyResponse;

public interface CompanyService {
    public CompanyResponse addCompany(CreateCompanyRequest createCompanyRequest);

}
