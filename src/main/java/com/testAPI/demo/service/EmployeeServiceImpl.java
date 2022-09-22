package com.testAPI.demo.service;

import com.testAPI.demo.exception.GlobalException;
import com.testAPI.demo.object.model.Employee;
import com.testAPI.demo.object.entity.CompanyEntity;
import com.testAPI.demo.object.entity.EmployeeEntity;
import com.testAPI.demo.object.mapper.EmployeeMapper;
import com.testAPI.demo.payload.request.CreateEmployeeRequest;
import com.testAPI.demo.payload.request.UpdateEmployeeRequest;
import com.testAPI.demo.payload.response.EmployeeResponse;
import com.testAPI.demo.repository.AddressRepository;
import com.testAPI.demo.repository.CompanyRepository;
import com.testAPI.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final CompanyRepository companyRepository;
    private final AddressRepository addressRepository;

    @Override
    public Employee addEmployee(CreateEmployeeRequest createEmployeeRequest) {
            EmployeeEntity employeeEntity = employeeMapper.toEntity(employeeMapper.fromCreateEmployeeRequest(createEmployeeRequest));
            CompanyEntity companyEntity = companyRepository.findOneByCompanyId(createEmployeeRequest.getCompanyId());
            if(null == companyEntity){
                throw new GlobalException("can not find company id : " + createEmployeeRequest.getCompanyId());
            }
            employeeRepository.save(employeeEntity);
            return employeeMapper.fromEmployee(employeeEntity);
    }

    @Override
    public Employee updateEmployee(UpdateEmployeeRequest updateEmployeeRequest) throws GlobalException {
        EmployeeEntity employeeEntity = employeeRepository.findOneByEmployeeId(updateEmployeeRequest.getEmployeeId());
        if(null == employeeEntity){
            throw new GlobalException("can not find id : " + updateEmployeeRequest.getEmployeeId());
        }
        EmployeeEntity employeeEntityToUpdate = employeeRepository.findOneByEmployeeId(updateEmployeeRequest.getEmployeeId());
        employeeEntityToUpdate.updateEmployeeEntity(employeeMapper.fromUpdateEmployeeRequest(updateEmployeeRequest));
        employeeRepository.save(employeeEntityToUpdate);
        return employeeMapper.fromEmployee(employeeEntityToUpdate);
    }

    @Override
    public Employee getEmployee(UUID id) {
        EmployeeEntity employeeEntity = employeeRepository.findOneByEmployeeId(id);
        if(null == employeeEntity){
            throw new GlobalException("can not find id : " + id);
        }
        return employeeMapper.fromEmployee(employeeEntity);
    }

    @Override
    @Transactional
    public Employee deleteEmployee(UUID id) {
        EmployeeEntity employeeEntity = employeeRepository.findOneByEmployeeId(id);
        if(null == employeeEntity){
            throw new GlobalException("can not find id : " + id);
        }
        Employee employee = employeeMapper.fromEmployee(employeeEntity);
        employeeRepository.deleteByEmployeeId(id);
        return employee;
    }

    @Override
    public List<EmployeeResponse> getListEmployee() throws GlobalException {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
        List<Employee> employeeList = employeeEntityList.stream().map(this::convertEmployee).collect(Collectors.toList());
        return employeeList.stream().map(this::convertToResponse).collect(Collectors.toList());

    }

    @Override
    public Employee convertEmployee(EmployeeEntity employeeEntity) {
        return employeeMapper.fromEmployee(employeeEntity);
    }
    @Override
    public EmployeeResponse convertToResponse(Employee employee) {
        EmployeeResponse employeeResponse = employeeMapper.toEmployeeResponse(employee);
        CompanyEntity companyEntity = companyRepository.findOneByCompanyId(employee.getCompanyId());
        employeeResponse.setCompanyName(companyEntity.getCompanyName());
        employeeResponse.setCompanyCity(addressRepository.findOneByAddressId(companyEntity.getAddressId()).getCity());
        return employeeResponse;
    }

}
