package com.testAPI.demo.service;

import com.testAPI.demo.object.entity.AddressEntity;
import com.testAPI.demo.object.entity.CompanyEntity;
import com.testAPI.demo.object.entity.EmployeeEntity;
import com.testAPI.demo.object.model.Company;
import com.testAPI.demo.object.model.Employee;
import com.testAPI.demo.payload.request.CreateCompanyRequest;
import com.testAPI.demo.payload.request.UpdateCompanyRequest;
import com.testAPI.demo.payload.request.UpdateEmployeeRequest;
import com.testAPI.demo.payload.response.CompanyResponse;
import com.testAPI.demo.payload.response.EmployeeResponse;

import java.util.List;
import java.util.UUID;

public interface CompanyService {
    public CompanyResponse addCompany(CreateCompanyRequest createCompanyRequest);
    public CompanyResponse updateCompany(UpdateCompanyRequest updateCompanyRequest);

    public CompanyResponse getCompany(UUID id);

    public CompanyResponse deleteCompany(UUID id);

    public List<CompanyResponse> getListCompany();

}
