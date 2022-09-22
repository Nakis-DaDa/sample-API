package com.testAPI.demo.object.entity;


import com.testAPI.demo.object.model.Employee;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="employee")
@Getter
@Setter
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @org.hibernate.annotations.Type(type="org.hibernate.type.UUIDCharType")
    @Column(name="employee_id")
    private UUID employeeId;
    @Column(name="first_name")
    private String firstname;
    @Column(name="last_name")
    private  String lastname;
    @Column(name="age")
    private int age;

    @Column(name="company_id")
    @org.hibernate.annotations.Type(type="org.hibernate.type.UUIDCharType")
    private UUID companyId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="company_id",insertable = false,updatable = false)
    private CompanyEntity companyEntity;

    public void updateEmployeeEntity(Employee employee) {
        this.firstname = employee.getFirstname();
        this.lastname = employee.getLastname();
        this.age = employee.getAge();
    }
}