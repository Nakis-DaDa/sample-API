package com.testAPI.demo.object.mapper;

import com.testAPI.demo.object.model.Employee;
import com.testAPI.demo.object.entity.EmployeeEntity;
import com.testAPI.demo.payload.request.CreateEmployeeRequest;
import com.testAPI.demo.payload.request.UpdateEmployeeRequest;
import com.testAPI.demo.payload.response.EmployeeResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {


    Employee fromEmployee(EmployeeEntity employeeEntity);
    EmployeeEntity toEntity(Employee employee);
    Employee fromCreateEmployeeRequest(CreateEmployeeRequest createEmployeeRequest);
    Employee fromUpdateEmployeeRequest(UpdateEmployeeRequest updateEmployeeRequest);

    EmployeeResponse toEmployeeResponse(Employee employee);
}