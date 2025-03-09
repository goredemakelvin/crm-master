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
   // @NotBlank(message = "Please enter your PhoneNumber")
    private String phoneNumber;

}
