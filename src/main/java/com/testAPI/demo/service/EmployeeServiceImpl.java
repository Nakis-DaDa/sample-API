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
    public Employee addEmployee(CreateEmployeeRequest createEmployeeRequest) throws GlobalException {
        try {
            EmployeeEntity employeeEntity = employeeMapper.toEntity(employeeMapper.fromCreateEmployeeRequest(createEmployeeRequest));
            employeeRepository.save(employeeEntity);
            log.info("Create Employee id : "+employeeEntity.getId());
            return employeeMapper.fromEmployee(employeeEntity);
        } catch (Exception ex) {
            log.error("Exception in "+getClass().getName()+" : "+ex.getMessage());
            throw new GlobalException(ex.getMessage());
        }
    }

    @Override
    public Employee updateEmployee(UpdateEmployeeRequest updateEmployeeRequest) throws GlobalException {
        try {
            if (employeeRepository.findById(updateEmployeeRequest.getId()).isPresent()) {
                EmployeeEntity employeeEntityToUpdate = employeeRepository.findById(updateEmployeeRequest.getId()).get();
                employeeEntityToUpdate.updateEmployeeEntity(employeeMapper.fromUpdateEmployeeRequest(updateEmployeeRequest));
                employeeRepository.save(employeeEntityToUpdate);
                log.info("Update Employee id : "+updateEmployeeRequest.getId());
                return employeeMapper.fromEmployee(employeeEntityToUpdate);
            } else {
                log.error(getClass().getName()+" can not find id : " + updateEmployeeRequest.getId());
                throw new GlobalException("can not find id : " + updateEmployeeRequest.getId());
            }
        } catch (Exception ex) {
            log.error("Exception in "+getClass().getName()+" : "+ex.getMessage());
            throw new GlobalException(ex.getMessage());
        }
    }

    @Override
    public Employee getEmployee(int id) throws GlobalException {
        try {
            if (employeeRepository.findById(id).isPresent()) {
                EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
                log.info("Get Employee id : "+id);
                return employeeMapper.fromEmployee(employeeEntity);
            } else {
                log.error(getClass().getName()+" can not find id : " + id);
                throw new GlobalException("can not find id : " + id);
            }
        } catch (Exception ex) {
            log.error("Exception in "+getClass().getName()+" : "+ex.getMessage());
            throw new GlobalException(ex.getMessage());
        }
    }

    @Override
    public Employee deleteEmployee(int id) throws GlobalException {
        try {
            if (employeeRepository.findById(id).isPresent()) {
                EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
                Employee employee = employeeMapper.fromEmployee(employeeEntity);
                employeeRepository.deleteById(id);
                log.info("Delete Employee id : "+id);
                return employee;
            } else {
                log.error(getClass().getName()+" can not find id : " + id);
                throw new GlobalException("can not find id : " + id);
            }
        } catch (Exception ex) {
            log.error("Exception in "+getClass().getName()+" : "+ex.getMessage());
            throw new GlobalException(ex.getMessage());
        }
    }

    @Override
    public List<Employee> getListEmployee() throws GlobalException {
        try {
            List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
            log.info("Get all Employee");
            return employeeEntityList.stream().map(this::convertEmployee).collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Exception in "+getClass().getName()+" : "+ex.getMessage());
            throw new GlobalException(ex.getMessage());
        }
    }

    @Override
    public Employee convertEmployee(EmployeeEntity employeeEntity) {
        return employeeMapper.fromEmployee(employeeEntity);
    }

}
