package com.cicosy.crm.service;

import com.cicosy.crm.entity.Industry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class IndustryService extends AbstractService<Industry> {
    public abstract Page<Industry> findAllIndustries(Pageable pageable);
}
