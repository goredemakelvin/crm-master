package com.cicosy.crm.service;

import com.cicosy.crm.entity.Lead;

public interface LeadConversionService {

    void convertLead(Lead lead, int stage);
}
