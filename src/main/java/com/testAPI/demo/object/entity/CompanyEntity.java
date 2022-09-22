package com.testAPI.demo.object.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="company")
@Getter
@Setter
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @org.hibernate.annotations.Type(type="org.hibernate.type.UUIDCharType")
    @Column(name="company_id")
    private UUID companyId;

    @Column(name="company_name")
    private String companyName;

    @Column(name="address_id")
    @org.hibernate.annotations.Type(type="org.hibernate.type.UUIDCharType")
    private UUID addressId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="address_id",insertable = false,updatable = false)
    private AddressEntity address;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="company_id")
    private Set<EmployeeEntity> employee;
}
