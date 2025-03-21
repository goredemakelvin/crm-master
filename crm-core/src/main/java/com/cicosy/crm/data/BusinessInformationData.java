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

    public BusinessInformationData getCustomerBusinessInformation(BusinessInformation businessInformation) {
        if(businessInformation!=null) {
            this.companyName=businessInformation.getCompanyName();
            if(businessInformation.getIndustry()!=null) {
                Industry i = businessInformation.getIndustry();
                this.industry=i.getName();
            }
            this.jobTitle=businessInformation.getJobTitle();
            if(businessInformation.getAccountManager()!=null) {
                this.accountManager=businessInformation.getAccountManager().getFirstName();
            }

            this.customerId= businessInformation.getCustomer().getId();
            return this;
        }
        return null;
    }
}
