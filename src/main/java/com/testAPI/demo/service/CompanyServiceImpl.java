package com.testAPI.demo.service;

import com.testAPI.demo.exception.GlobalException;
import com.testAPI.demo.object.entity.AddressEntity;
import com.testAPI.demo.object.entity.CompanyEntity;
import com.testAPI.demo.object.mapper.AddressMapper;
import com.testAPI.demo.object.mapper.CompanyMapper;
import com.testAPI.demo.payload.request.CreateCompanyRequest;
import com.testAPI.demo.payload.response.CompanyResponse;
import com.testAPI.demo.repository.AddressRepository;
import com.testAPI.demo.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService{

    private final CompanyMapper companyMapper;
    private final AddressMapper addressMapper;
    private final CompanyRepository companyRepository;
    private final AddressRepository addressRepository;

    @Override
    public CompanyResponse addCompany(CreateCompanyRequest createCompanyRequest) {
        AddressEntity addressEntity = getAddress(createCompanyRequest);
        createCompanyRequest.setAddress(addressMapper.fromEntity(addressEntity));

        CompanyEntity companyEntity = companyMapper.toEntity(companyMapper.fromCreateCompanyRequest(createCompanyRequest));
        companyRepository.save(companyEntity);

        CompanyResponse companyResponse = companyMapper.toRespone(companyMapper.fromEntity(companyEntity));
        //companyResponse.setAddress(addressMapper.fromEntity(addressEntity));
        return companyResponse;
    }

    private AddressEntity getAddress(CreateCompanyRequest createCompanyRequest){
        AddressEntity addressEntity;
        if(null == createCompanyRequest.getAddress().getAddressId()){
            if (createCompanyRequest.getAddress().getStreetAddress() == null ||
                    createCompanyRequest.getAddress().getCity() == null ||
                    createCompanyRequest.getAddress().getZipCode() == null){
                throw new GlobalException("some address information is missing.");
            }
            addressEntity = addressMapper.toEntity(createCompanyRequest.getAddress());
            addressRepository.save(addressEntity);
        } else {
            addressEntity = addressRepository.findOneByAddressId(createCompanyRequest.getAddress().getAddressId());
            if (null == addressEntity){
                throw new GlobalException("can not find company id : " + createCompanyRequest.getAddress().getAddressId());
            }
        }
        return addressEntity;
    }
}
