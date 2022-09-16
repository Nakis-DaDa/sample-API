package com.testAPI.demo.model;

import com.testAPI.demo.payload.CreateEmployeeRequest;
import com.testAPI.demo.payload.UpdateEmployeeRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {


    Employee fromEmployee(EmployeeEntity employeeEntity);
    EmployeeEntity toEntity(Employee employee);
    Employee fromCreateEmployeeRequest(CreateEmployeeRequest createEmployeeRequest);
    Employee fromUpdateEmployeeRequest(UpdateEmployeeRequest updateEmployeeRequest);
}