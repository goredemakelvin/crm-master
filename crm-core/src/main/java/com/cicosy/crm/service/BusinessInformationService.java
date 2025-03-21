package com.cicosy.crm.service;

import com.cicosy.crm.data.BusinessFormData;
import com.cicosy.crm.entity.BusinessInformation;
import com.cicosy.crm.entity.Customer;

import java.util.Optional;

public abstract class BusinessInformationService  extends AbstractService<BusinessInformation> {
    public abstract void saveBusinessInformation(BusinessFormData BusinessFormData);
    public abstract Optional<BusinessInformation> findByCustomer(Customer customer);
}
