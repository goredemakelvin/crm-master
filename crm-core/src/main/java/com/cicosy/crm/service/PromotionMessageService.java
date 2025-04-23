package com.cicosy.crm.service;

import com.cicosy.crm.entity.LeadSegment;
import com.cicosy.crm.entity.PromotionMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public abstract class PromotionMessageService extends AbstractService<PromotionMessage> {
    public abstract Page<PromotionMessage> findAllPromotionMessages(Pageable pageable);
    public abstract List<PromotionMessage> findAllByLeadSegment(LeadSegment leadSegment);
}
