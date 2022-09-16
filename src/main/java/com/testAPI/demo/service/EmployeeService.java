package com.testAPI.demo.service;

import com.testAPI.demo.model.Employee;
import com.testAPI.demo.model.EmployeeEntity;
import com.testAPI.demo.payload.CreateEmployeeRequest;
import com.testAPI.demo.payload.UpdateEmployeeRequest;

import java.util.List;

public interface EmployeeService {

    public Employee addEmployee(CreateEmployeeRequest createEmployeeRequest);

    public Employee updateEmployee(UpdateEmployeeRequest updateEmployeeRequest);

    public Employee getEmployee(int id);

    public Employee deleteEmployee(int id);

    public List<Employee> getListEmployee();

    public Employee convertEmployee(EmployeeEntity employeeEntity);
}