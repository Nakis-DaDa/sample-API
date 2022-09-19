package com.testAPI.demo.service;

import com.testAPI.demo.model.Employee;
import com.testAPI.demo.model.EmployeeEntity;
import com.testAPI.demo.payload.CreateEmployeeRequest;
import com.testAPI.demo.payload.UpdateEmployeeRequest;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    public Employee addEmployee(CreateEmployeeRequest createEmployeeRequest);

    public Employee updateEmployee(UpdateEmployeeRequest updateEmployeeRequest);

    public Employee getEmployee(UUID id);

    public Employee deleteEmployee(UUID id);

    public List<Employee> getListEmployee();

    public Employee convertEmployee(EmployeeEntity employeeEntity);
}