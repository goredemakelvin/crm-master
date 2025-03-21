package com.cicosy.crm.data;

import com.cicosy.crm.entity.BusinessInformation;
import com.cicosy.crm.entity.Customer;
import com.cicosy.crm.entity.Industry;
import lombok.Data;

@Data
public class BusinessInformationData {

    private Customer customer;
    private String companyName;
    private String industry;
    private String jobTitle;
    private String accountManager;
    private Long customerId;

    public BusinessInformationData buildCustomerBusinessInformation(Customer customer) {
        if(customer.getBusinessInformation()!=null) {
            BusinessInformation b = customer.getBusinessInformation();
            this.companyName=b.getCompanyName();
            if(b.getIndustry()!=null) {
                Industry i = b.getIndustry();
                this.industry=i.getName();
            }
            this.jobTitle=b.getJobTitle();
            if(b.getAccountManager()!=null) {
                this.accountManager=b.getAccountManager().getFirstName();
            }

            this.customerId= customer.getId();
            return this;
        }
        return null;
    }
}
