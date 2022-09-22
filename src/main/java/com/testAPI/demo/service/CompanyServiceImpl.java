package com.testAPI.demo.service;

import com.testAPI.demo.exception.GlobalException;
import com.testAPI.demo.object.entity.AddressEntity;
import com.testAPI.demo.object.entity.CompanyEntity;
import com.testAPI.demo.object.entity.EmployeeEntity;
import com.testAPI.demo.object.mapper.AddressMapper;
import com.testAPI.demo.object.mapper.CompanyMapper;
import com.testAPI.demo.object.model.Company;
import com.testAPI.demo.object.model.Employee;
import com.testAPI.demo.payload.request.CreateCompanyRequest;
import com.testAPI.demo.payload.request.UpdateCompanyRequest;
import com.testAPI.demo.payload.response.CompanyResponse;
import com.testAPI.demo.payload.response.EmployeeResponse;
import com.testAPI.demo.repository.AddressRepository;
import com.testAPI.demo.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

        return companyMapper.toRespone(companyMapper.fromEntity(companyEntity));
    }

    @Override
    public CompanyResponse updateCompany(UpdateCompanyRequest updateCompanyRequest) {
        CompanyEntity companyEntity = companyRepository.findOneByCompanyId(updateCompanyRequest.getCompanyId());
        if(null == companyEntity){
            throw new GlobalException("can not find company id : " + updateCompanyRequest.getCompanyId());
        }
        companyEntity.setCompanyName(updateCompanyRequest.getCompanyName());
        companyEntity.setAddressId(updateCompanyRequest.getAddress().getAddressId());
        companyRepository.save(companyEntity);
        return companyMapper.toRespone(companyMapper.fromEntity(companyEntity));
    }

    @Override
    public CompanyResponse getCompany(UUID id) {
        CompanyEntity companyEntity = companyRepository.findOneByCompanyId(id);
        if(null == companyEntity){
            throw new GlobalException("can not find company id : " + id);
        }
        return companyMapper.toRespone(companyMapper.fromEntity(companyEntity));
    }

    @Override
    @Transactional
    public CompanyResponse deleteCompany(UUID id) {
        CompanyEntity companyEntity = companyRepository.findOneByCompanyId(id);
        if(null == companyEntity){
            throw new GlobalException("can not find id : " + id);
        }
        CompanyResponse companyResponse = companyMapper.toRespone(companyMapper.fromEntity(companyEntity));
        companyRepository.deleteByCompanyId(id);
        return companyResponse;
    }

    @Override
    public List<CompanyResponse> getListCompany() {
        List<CompanyEntity> companyEntityList = companyRepository.findAll();
        List<Company> companyList = companyEntityList.stream().map(this::convertCompany).collect(Collectors.toList());
        return companyList.stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    @Override
    public Company convertCompany(CompanyEntity companyEntity) {
        return companyMapper.fromEntity(companyEntity);
    }

    @Override
    public CompanyResponse convertToResponse(Company company) {
        return companyMapper.toRespone(company);
    }

    @Override
    public AddressEntity getAddress(CreateCompanyRequest createCompanyRequest){
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
