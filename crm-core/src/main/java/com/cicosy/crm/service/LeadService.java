package com.cicosy.crm.service;

import com.cicosy.crm.data.LeadData;
import com.cicosy.crm.entity.Lead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract  class LeadService extends AbstractService<Lead>{
    public abstract Page<Lead> findAllLeads(Pageable pageable);
    public abstract Lead createLead(LeadData leadData);
}
