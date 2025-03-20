package com.cicosy.crm.web;

import com.cicosy.crm.entity.Customer;
import lombok.Data;

@Data
public class CustomerBusinessInformation {
    private Customer customer;
    private String companyName;
    private String industry;
    private String jobTitle;
    private String accountManager;
    private Long customerId;

    public CustomerBusinessInformation buildCustomerBusinessInformation(Customer customer) {
        if(customer.getBusinessInformation()!=null) {
            this.companyName=customer.getBusinessInformation().getCompanyName();
            this.industry=customer.getBusinessInformation().getIndustry().getName();
            this.jobTitle=customer.getBusinessInformation().getJobTitle();
            this.accountManager=customer.getBusinessInformation().getAccountManager();
            this.customerId=customer.getId();
            return this;
        }
        return null;
    }
}
