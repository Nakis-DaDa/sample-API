package com.testAPI.demo.controller;

import com.testAPI.demo.exception.GlobalException;
import com.testAPI.demo.object.model.Employee;
import com.testAPI.demo.payload.request.CreateEmployeeRequest;
import com.testAPI.demo.payload.request.UpdateEmployeeRequest;
import com.testAPI.demo.payload.response.EmployeeResponse;
import com.testAPI.demo.service.EmployeeService;
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
@RequestMapping(path = "api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeResponse> addEmployee(@Valid @RequestBody CreateEmployeeRequest createEmployeeRequest) throws GlobalException {
        EmployeeResponse employeeResponse = employeeService.addEmployee(createEmployeeRequest);
        URI location = URI.create(String.format("/api/v1/employees/%s", employeeResponse.getEmployeeId()));
        return ResponseEntity.created(location).body(employeeResponse);
    }

    @PutMapping
    public ResponseEntity<EmployeeResponse> updateEmployee(@Valid @RequestBody UpdateEmployeeRequest updateEmployeeRequest) throws GlobalException {
        return ResponseEntity.ok().body(employeeService.updateEmployee(updateEmployeeRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployee(@Valid @PathVariable UUID id) throws GlobalException {
        return ResponseEntity.ok().body(employeeService.getEmployee(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeResponse> deleteEmployee(@Valid @PathVariable UUID id) throws GlobalException {
        return ResponseEntity.ok().body(employeeService.deleteEmployee(id));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getListEmployee() throws GlobalException {
        return ResponseEntity.ok().body(employeeService.getListEmployee());
    }

}
