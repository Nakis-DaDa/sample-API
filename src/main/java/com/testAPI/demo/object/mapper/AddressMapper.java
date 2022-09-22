package com.testAPI.demo.object.mapper;

import com.testAPI.demo.object.model.Address;
import com.testAPI.demo.object.entity.AddressEntity;
import com.testAPI.demo.payload.request.CreateAddressRequest;
import com.testAPI.demo.payload.request.UpdateAddressRequest;
import com.testAPI.demo.payload.response.AddressResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressEntity toEntity(Address address);
    Address fromEntity(AddressEntity address);

    Address fromCreateAddressRequest(CreateAddressRequest createAddressRequest);
    Address fromUpdateAddressRequest(UpdateAddressRequest updateAddressRequest);
    AddressResponse toAddressResponse(Address address);


}
