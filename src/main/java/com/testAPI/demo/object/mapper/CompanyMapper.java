package com.testAPI.demo.object.mapper;

import com.testAPI.demo.object.model.Company;
import com.testAPI.demo.object.entity.CompanyEntity;
import com.testAPI.demo.payload.request.CreateCompanyRequest;
import com.testAPI.demo.payload.response.CompanyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    @Mapping(source = "address.addressId",target = "addressId")
    Company fromCreateCompanyRequest(CreateCompanyRequest createCompanyRequest);

    CompanyEntity toEntity(Company company);
    CompanyResponse toRespone(Company company);
    Company fromEntity(CompanyEntity companyEntity);

}
