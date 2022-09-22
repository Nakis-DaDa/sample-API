package com.testAPI.demo.repository;

import com.testAPI.demo.object.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,UUID> {
    EmployeeEntity findOneByEmployeeId(UUID id);
    void deleteByEmployeeId(UUID id);
}
