package com.testAPI.demo.repository;

import com.testAPI.demo.object.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity,UUID>{
    CompanyEntity findOneByCompanyId(UUID company_id);
}
