package com.cicosy.crm.api.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Customer Entity")
public class CustomerRegistrationData {

    @Schema(description = "FirstName  of the customer", example = "John")
    private String firstName;
    @Schema(description = "LastName  of the customer", example = "Doe")
    private String lastName;
    @Schema(description = "Email  of the customer", example = "john@gmail.com")
    private String emailAddress;
    @Schema(description = "City  of the customer", example = "Harare")
    private String city;
    @Schema(description = "Country  of the customer", example = "10")
    private String country;
    @Schema(description = "Loyalty Points  of the customer", example = "10")
    private Integer loyaltyPoints;
    @Schema(description = "Phone Number of the customer", example = "000000")
    private String phoneNumber;
    @Schema(description = "Company of the customer", example = "M and N  Engineering")
    private String companyName;
    @Schema(description = "Industry of the customer", example = "Mining")
    private String industry;
    @Schema(description = "Job title of the customer", example = "Software Engineer")
    private String jobTitle;
    @Schema(description = "A person responsible for managing the customer", example = "John Doe")
    private String accountManager;
    @Schema(description = "Unique identifier of the customer", example = "1")
    private Long customerId;



}
