package com.testAPI.demo.repository;

import com.testAPI.demo.object.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, UUID> {
    AddressEntity findOneByAddressId(UUID addressId);
}
