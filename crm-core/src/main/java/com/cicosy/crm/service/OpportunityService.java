package com.cicosy.crm.service;

import com.cicosy.crm.entity.Opportunity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class OpportunityService extends AbstractService<Opportunity> {
    public abstract Page<Opportunity> findAllOpportunities(Pageable pageable);
}
