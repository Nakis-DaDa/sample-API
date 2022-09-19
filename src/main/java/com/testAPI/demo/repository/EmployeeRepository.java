package com.testAPI.demo.repository;

import com.testAPI.demo.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Integer> {
    EmployeeEntity findOneById(UUID id);

    void deleteById(UUID id);
}
