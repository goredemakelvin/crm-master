package com.cicosy.crm.service;

import com.cicosy.crm.entity.BusinessInformation;

public abstract class BusinessInformationService  extends AbstractService<BusinessInformation> {
    public abstract void saveBuinessInformation(BusinessInformation businessInformation);
}
