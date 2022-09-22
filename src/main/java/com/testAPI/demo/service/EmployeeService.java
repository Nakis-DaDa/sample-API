package com.testAPI.demo.service;

import com.testAPI.demo.object.model.Employee;
import com.testAPI.demo.object.entity.EmployeeEntity;
import com.testAPI.demo.payload.request.CreateEmployeeRequest;
import com.testAPI.demo.payload.request.UpdateEmployeeRequest;
import com.testAPI.demo.payload.response.EmployeeResponse;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    public EmployeeResponse addEmployee(CreateEmployeeRequest createEmployeeRequest);

    public EmployeeResponse updateEmployee(UpdateEmployeeRequest updateEmployeeRequest);

    public EmployeeResponse getEmployee(UUID id);

    public EmployeeResponse deleteEmployee(UUID id);

    public List<EmployeeResponse> getListEmployee();

    public Employee convertEmployee(EmployeeEntity employeeEntity);
    public EmployeeResponse convertToResponse(Employee employee);
}