package com.cicosy.crm.service;

import com.cicosy.crm.entity.Opportunity;
import com.cicosy.crm.repo.OpportunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OpportunityServiceImpl extends OpportunityService {

    @Autowired
    private OpportunityRepository opportunityRepository;

    @Override
    public Opportunity save(Opportunity Opportunity) {
        return opportunityRepository.save(Opportunity);
    }

    @Override
    public Optional<Opportunity> findById(Long id) {
        return opportunityRepository.findById(id);
    }

    @Override
    public List<Opportunity> findAll() {
        return opportunityRepository.findAll();
    }

    @Override
    public Page<Opportunity> findAllOpportunities(Pageable pageable) {
        return opportunityRepository.findAll(pageable);
    }
}
