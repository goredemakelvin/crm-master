package com.cicosy.crm.service;

import com.cicosy.crm.entity.LeadSegment;
import com.cicosy.crm.entity.PromotionMessage;
import com.cicosy.crm.repo.PromotionMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromotionMessageServiceImpl extends PromotionMessageService {

    @Autowired
    private PromotionMessageRepository promotionMessageRepository;

    @Override
    public PromotionMessage save(PromotionMessage PromotionMessage) {
        return promotionMessageRepository.save(PromotionMessage);
    }

    @Override
    public Optional<PromotionMessage> findById(Long id) {
        return promotionMessageRepository.findById(id);
    }

    @Override
    public List<PromotionMessage> findAll() {
        return promotionMessageRepository.findAll();
    }

    @Override
    public Page<PromotionMessage> findAllPromotionMessages(Pageable pageable) {
        return promotionMessageRepository.findAll(pageable);
    }

    @Override
    public List<PromotionMessage> findAllByLeadSegment(LeadSegment leadSegment) {
        return promotionMessageRepository.findByLeadSegment(leadSegment);
    }


}
