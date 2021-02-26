package com.paypal.bfs.test.employeeserv.api.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {



        private String id;
        @Size(min=1,max = 255,message = "First name should have atleast 2 characters and can not exceed 255 characters")
        @NotNull(message = "First name should have atleast 2 characters and can not exceed 255 characters")
        @NotBlank(message = "First name should have atleast 2 characters and can not exceed 255 characters")
        private String firstName;
        @Size(min=1,max = 255,message = "Last name should have atleast 2 characters and can not exceed 255 characters")
        @NotNull(message = "Last name should have atleast 2 characters and can not exceed 255 characters")
        @NotBlank(message = "Last name should have atleast 2 characters and can not exceed 255 characters")
        private String lastName;
        @Past
        @JsonFormat(pattern="yyyy-MM-dd")
        private LocalDate dateOfBirth;
        @NotNull(message="Address can not be null")
        @Valid
        Address address;



}
