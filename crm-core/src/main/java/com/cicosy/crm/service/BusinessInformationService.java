package com.cicosy.crm.service;

import com.cicosy.crm.data.BusinessFormData;
import com.cicosy.crm.entity.BusinessInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class BusinessInformationService extends AbstractService<BusinessInformation> {
    public abstract void saveBusinessInformation(BusinessFormData BusinessFormData);

    public abstract Page<BusinessInformation> findAllBusinessInformation(Pageable pageable);

}
