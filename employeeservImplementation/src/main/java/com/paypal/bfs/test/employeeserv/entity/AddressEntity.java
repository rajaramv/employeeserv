package com.paypal.bfs.test.employeeserv.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class AddressEntity {


    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private int zipCode;
}
