package com.testAPI.demo.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee")
@Data
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
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