package com.testAPI.demo.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="employee")
@Data
public class EmployeeEntity {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @org.hibernate.annotations.Type(type="org.hibernate.type.UUIDCharType")
    @Column(name="id")
    private UUID id;
    @Column(name="first_name")
    private String firstname;
    @Column(name="last_name")
    private  String lastname;
    @Column(name="age")
    private int age;
    public void updateEmployeeEntity(Employee employee) {
        this.firstname = employee.getFirstname();
        this.lastname = employee.getLastname();
        this.age = employee.getAge();
    }
}