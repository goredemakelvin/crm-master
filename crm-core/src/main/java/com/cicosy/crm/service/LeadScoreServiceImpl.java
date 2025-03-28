package com.cicosy.crm.service;

import com.cicosy.crm.data.LeadData;
import com.cicosy.crm.entity.Lead;
import com.cicosy.crm.entity.LeadScore;
import com.cicosy.crm.repo.LeadRepository;
import com.cicosy.crm.repo.LeadScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeadScoreServiceImpl extends LeadScoreService {

    @Autowired
    private LeadScoreRepository leadScoreRepository;
    @Autowired
    private LeadRepository leadRepository;

    @Override
    public LeadScore save(LeadScore leadScore) {
        return leadScoreRepository.save(leadScore);
    }

    @Override
    public Optional<LeadScore> findById(Long id) {
        return leadScoreRepository.findById(id);
    }

    @Override
    public List<LeadScore> findAll() {
        return leadScoreRepository.findAll();
    }

    @Override
    public void assignScore(LeadData leadData, Lead lead) {
        if (leadData.getCompanySize() > 0) {
            LeadScore leadScore = new LeadScore();
            leadScore.setLead(lead);
            leadScore.setScore(10);
            leadScoreRepository.save(leadScore);
            lead.setLeadScore(leadScore);
            leadRepository.save(lead);
        }

    }
}
