package com.cicosy.crm.service;

import com.cicosy.crm.entity.PromotionMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class PromotionMessageService extends AbstractService<PromotionMessage> {
    public abstract Page<PromotionMessage> findAllPromotionMessages(Pageable pageable);
}
