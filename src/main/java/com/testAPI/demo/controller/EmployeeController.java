package com.testAPI.demo.controller;

import com.testAPI.demo.exception.GlobalException;
import com.testAPI.demo.model.Employee;
import com.testAPI.demo.payload.CreateEmployeeRequest;
import com.testAPI.demo.payload.UpdateEmployeeRequest;
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
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody CreateEmployeeRequest createEmployeeRequest) throws GlobalException {
        Employee employee = employeeService.addEmployee(createEmployeeRequest);
        URI location = URI.create(String.format("/api/v1/employees/%s", employee.getId()));
        return ResponseEntity.created(location).body(employee);
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody UpdateEmployeeRequest updateEmployeeRequest) throws GlobalException {
        return ResponseEntity.ok().body(employeeService.updateEmployee(updateEmployeeRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@Valid @PathVariable UUID id) throws GlobalException {
        return ResponseEntity.ok().body(employeeService.getEmployee(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployee(@Valid @PathVariable UUID id) throws GlobalException {
        return ResponseEntity.ok().body(employeeService.deleteEmployee(id));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getListEmployee() throws GlobalException {
        return ResponseEntity.ok().body(employeeService.getListEmployee());
    }

}
