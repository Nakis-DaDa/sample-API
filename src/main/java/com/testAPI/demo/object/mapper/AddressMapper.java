package com.testAPI.demo.object.mapper;

import com.testAPI.demo.object.model.Address;
import com.testAPI.demo.object.entity.AddressEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressEntity toEntity(Address address);
    Address fromEntity(AddressEntity address);

}
