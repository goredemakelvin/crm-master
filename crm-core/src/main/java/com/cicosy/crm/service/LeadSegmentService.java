package com.cicosy.crm.service;

import com.cicosy.crm.entity.LeadSegment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class LeadSegmentService extends AbstractService<LeadSegment> {

    public abstract Page<LeadSegment> findAllLeadSegment(Pageable pageable);

}
