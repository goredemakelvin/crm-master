package com.cicosy.crm.data;

import lombok.Data;

@Data
public class BusinessInformationData {
    private String companyName;
    private String industry;
    private String jobTitle;
    private String accountManager;
    private Long customerId;
}
