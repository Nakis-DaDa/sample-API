package com.testAPI.demo.controller;

import com.testAPI.demo.exception.GlobalException;
import com.testAPI.demo.object.model.Employee;
import com.testAPI.demo.payload.request.CreateCompanyRequest;
import com.testAPI.demo.payload.request.UpdateCompanyRequest;
import com.testAPI.demo.payload.request.UpdateEmployeeRequest;
import com.testAPI.demo.payload.response.CompanyResponse;
import com.testAPI.demo.payload.response.EmployeeResponse;
import com.testAPI.demo.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/companies")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyResponse> addCompany(@Valid @RequestBody CreateCompanyRequest createCompanyRequest) throws GlobalException {
        CompanyResponse companyResponse = companyService.addCompany(createCompanyRequest);
        URI location = URI.create(String.format("/api/v1/companies/%s", companyResponse.getCompanyId()));
        return ResponseEntity.created(location).body(companyResponse);
    }

    @PutMapping
    public ResponseEntity<CompanyResponse> updateCompany(@Valid @RequestBody UpdateCompanyRequest updateCompanyRequest) throws GlobalException {
        return ResponseEntity.ok().body(companyService.updateCompany(updateCompanyRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getCompany(@Valid @PathVariable UUID id) throws GlobalException {
        return ResponseEntity.ok().body(companyService.getCompany(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CompanyResponse> deleteCompany(@Valid @PathVariable UUID id) throws GlobalException {
        return ResponseEntity.ok().body(companyService.deleteCompany(id));
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getListCompany() throws GlobalException {
        return ResponseEntity.ok().body(companyService.getListCompany());
    }
}
