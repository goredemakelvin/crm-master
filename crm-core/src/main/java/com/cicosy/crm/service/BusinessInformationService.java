package com.cicosy.crm.service;

import com.cicosy.crm.data.BusinessFormData;
import com.cicosy.crm.entity.BusinessInformation;
import com.cicosy.crm.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public abstract class BusinessInformationService  extends AbstractService<BusinessInformation> {
    public abstract void saveBusinessInformation(BusinessFormData BusinessFormData);
    public abstract Optional<BusinessInformation> findByCustomer(Customer customer);
    public abstract Page<BusinessInformation> findAllBusinessInformation(Pageable pageable);

}
