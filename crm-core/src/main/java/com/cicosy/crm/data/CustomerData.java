package com.cicosy.crm.data;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class CustomerData {
    private Long id;
    @NotBlank(message = "Please enter your FirstName")
    private String firstName;
    @NotBlank(message = "Please enter your LastName")
    private String lastName;
    private String emailAddress;
    private Integer loyaltyPoints;
    private String  customerID;
    private String companyName ;
    private String city;
    private String country;
    private String phonenumber1;
    private String phonenumber2 ;
    private LocalDate subscriptionDate;
    private String website;

}
