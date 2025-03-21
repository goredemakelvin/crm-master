package com.cicosy.crm.data;

import lombok.Data;

@Data
public class BusinessFormData {

    private String companyName;
    private Long industry;
    private Long city;
    private Long country;
    private String jobTitle;
    private String accountManager;
    private Long customerId;
    private int companySize;
}
