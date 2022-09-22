package com.testAPI.demo.service;

import com.testAPI.demo.object.entity.AddressEntity;
import com.testAPI.demo.object.model.Address;
import com.testAPI.demo.payload.request.CreateAddressRequest;
import com.testAPI.demo.payload.request.UpdateAddressRequest;
import com.testAPI.demo.payload.response.AddressResponse;

import java.util.List;
import java.util.UUID;

public interface AddressService {
    public AddressResponse addAddress(CreateAddressRequest createAddressRequest);
    public AddressResponse updateAddress(UpdateAddressRequest updateAddressRequest);
    public AddressResponse getAddress(UUID id);
    public AddressResponse deleteAddress(UUID id);
    public List<AddressResponse> getListAddress();
    public Address convertAddress(AddressEntity addressEntity);
    public AddressResponse convertToResponse(Address address);
}
