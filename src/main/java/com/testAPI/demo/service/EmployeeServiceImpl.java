package com.testAPI.demo.service;

import com.testAPI.demo.exception.GlobalException;
import com.testAPI.demo.model.Employee;
import com.testAPI.demo.model.EmployeeEntity;
import com.testAPI.demo.model.EmployeeMapper;
import com.testAPI.demo.payload.CreateEmployeeRequest;
import com.testAPI.demo.payload.UpdateEmployeeRequest;
import com.testAPI.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public Employee addEmployee(CreateEmployeeRequest createEmployeeRequest) {
            EmployeeEntity employeeEntity = employeeMapper.toEntity(employeeMapper.fromCreateEmployeeRequest(createEmployeeRequest));
            employeeRepository.save(employeeEntity);
            return employeeMapper.fromEmployee(employeeEntity);
    }

    @Override
    public Employee updateEmployee(UpdateEmployeeRequest updateEmployeeRequest) throws GlobalException {
        EmployeeEntity employeeEntity = employeeRepository.getReferenceById(updateEmployeeRequest.getId());
        if(null == employeeEntity){
            throw new GlobalException("can not find id : " + updateEmployeeRequest.getId());
        }
        EmployeeEntity employeeEntityToUpdate = employeeRepository.findById(updateEmployeeRequest.getId()).get();
        employeeEntityToUpdate.updateEmployeeEntity(employeeMapper.fromUpdateEmployeeRequest(updateEmployeeRequest));
        employeeRepository.save(employeeEntityToUpdate);
        return employeeMapper.fromEmployee(employeeEntityToUpdate);
    }

    @Override
    public Employee getEmployee(int id) {
        EmployeeEntity employeeEntity = employeeRepository.getReferenceById(id);
        if(null == employeeEntity){
            throw new GlobalException("can not find id : " + id);
        }
        return employeeMapper.fromEmployee(employeeEntity);
    }

    @Override
    public Employee deleteEmployee(int id) {
        EmployeeEntity employeeEntity = employeeRepository.getReferenceById(id);
        if(null == employeeEntity){
            throw new GlobalException("can not find id : " + id);
        }
        Employee employee = employeeMapper.fromEmployee(employeeEntity);
        employeeRepository.deleteById(id);
        return employee;
    }

    @Override
    public List<Employee> getListEmployee() throws GlobalException {
            List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
            return employeeEntityList.stream().map(this::convertEmployee).collect(Collectors.toList());
    }

    @Override
    public Employee convertEmployee(EmployeeEntity employeeEntity) {
        return employeeMapper.fromEmployee(employeeEntity);
    }

}
