package com.testAPI.demo.payload.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@Data
public class UpdateEmployeeRequest implements Serializable {

    @NotNull(message ="employee id cannot be null")
    private UUID employeeId;

    @NotNull(message ="Firstname cannot be null")
    @Size(min = 1, max = 45, message = "Firstname must be between 1 and 45 characters")
    private String firstname;

    @NotNull(message ="Lastname cannot be null")
    @Size(min = 1, max = 45, message = "Lastname must be between 1 and 45 characters")
    private String lastname;

    @NotNull(message ="Age cannot be null")
    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 65, message = "Age should not be greater than 65")
    private int age;

    @NotNull(message ="company id cannot be null")
    private UUID companyId;
}
