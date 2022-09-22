package com.testAPI.demo.service;

import com.testAPI.demo.exception.GlobalException;
import com.testAPI.demo.object.entity.AddressEntity;
import com.testAPI.demo.object.entity.CompanyEntity;
import com.testAPI.demo.object.mapper.AddressMapper;
import com.testAPI.demo.object.model.Address;
import com.testAPI.demo.object.model.Company;
import com.testAPI.demo.payload.request.CreateAddressRequest;
import com.testAPI.demo.payload.request.UpdateAddressRequest;
import com.testAPI.demo.payload.response.AddressResponse;
import com.testAPI.demo.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService{

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    @Override
    public AddressResponse addAddress(CreateAddressRequest createAddressRequest) {
        AddressEntity addressEntity = addressMapper.toEntity(addressMapper.fromCreateAddressRequest(createAddressRequest));
        addressRepository.save(addressEntity);
        return addressMapper.toAddressResponse(addressMapper.fromEntity(addressEntity));
    }

    @Override
    public AddressResponse updateAddress(UpdateAddressRequest updateAddressRequest) {
        AddressEntity addressEntity = addressRepository.findOneByAddressId(updateAddressRequest.getAddressId());
        if(null == addressEntity){
            throw new GlobalException("can not find id : " + updateAddressRequest.getAddressId());
        }
        addressEntity.setStreetAddress(updateAddressRequest.getStreetAddress());
        addressEntity.setCity(updateAddressRequest.getCity());
        addressEntity.setState(updateAddressRequest.getState());
        addressEntity.setZipCode(updateAddressRequest.getZipCode());
        addressRepository.save(addressEntity);
        return addressMapper.toAddressResponse(addressMapper.fromEntity(addressEntity));
    }

    @Override
    public AddressResponse getAddress(UUID id) {
        AddressEntity addressEntity = addressRepository.findOneByAddressId(id);
        if(null == addressEntity){
            throw new GlobalException("can not find company id : " + id);
        }
        return addressMapper.toAddressResponse(addressMapper.fromEntity(addressEntity));
    }

    @Override
    @Transactional
    public AddressResponse deleteAddress(UUID id) {
        AddressEntity addressEntity = addressRepository.findOneByAddressId(id);
        if(null == addressEntity){
            throw new GlobalException("can not find company id : " + id);
        }
        AddressResponse addressResponse = addressMapper.toAddressResponse(addressMapper.fromEntity(addressEntity));
        addressRepository.deleteByAddressId(id);
        return addressResponse;
    }

    @Override
    public List<AddressResponse> getListAddress() {
        List<AddressEntity> addressEntityList = addressRepository.findAll();
        List<Address> addressList = addressEntityList.stream().map(this::convertAddress).collect(Collectors.toList());
        return addressList.stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    @Override
    public Address convertAddress(AddressEntity addressEntity) {
        return addressMapper.fromEntity(addressEntity);
    }

    @Override
    public AddressResponse convertToResponse(Address address) {
        return addressMapper.toAddressResponse(address);
    }
}
