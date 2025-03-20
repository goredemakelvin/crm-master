package com.cicosy.crm.data;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerData {
    private Long id;
    @NotBlank(message = "Please enter your FirstName")
    private String firstName;
    @NotBlank(message = "Please enter your LastName")
    private String lastName;
    private String emailAddress;
    private Long city;
    private Long country;
    private Integer loyaltyPoints;
    private String phoneNumber;

}
