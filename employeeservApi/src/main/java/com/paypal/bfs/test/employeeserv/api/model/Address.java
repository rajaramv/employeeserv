package com.paypal.bfs.test.employeeserv.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @NotNull(message = "Address Line 1 can not be empty")
    @NotBlank(message = "Address Line 1 can not be empty")
    private String addressLine1;
    private String addressLine2;
    @NotNull(message = "City can not be empty")
    @NotBlank(message = "City can not be empty")
    private String city;
    @NotNull(message = "State can not be empty")
    @NotBlank(message = "State can not be empty")
    private String state;
    @NotNull(message = "Country can not be empty")
    @NotBlank(message = "Country can not be empty")
    private String country;
    @NotNull(message = "Zip Code can not be empty")
    @Digits(integer = 5,fraction = 0,message="Zip code should be 5 digit long")
    @Min(value=10000, message="Zip code should be 5 digit long")
    @Max(value=99999, message="Zip code should be 5 digit long")
    private int zipCode;
}
