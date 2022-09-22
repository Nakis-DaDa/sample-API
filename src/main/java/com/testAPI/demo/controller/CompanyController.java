package com.testAPI.demo.controller;

import com.testAPI.demo.exception.GlobalException;
import com.testAPI.demo.payload.request.CreateCompanyRequest;
import com.testAPI.demo.payload.response.CompanyResponse;
import com.testAPI.demo.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

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
}
