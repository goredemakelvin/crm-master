package com.cicosy.crm.service;

import com.cicosy.crm.entity.LeadSegment;
import com.cicosy.crm.repo.LeadSegmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeadSegmentServiceImpl extends LeadSegmentService {

    @Autowired
    private LeadSegmentRepository leadSegmentRepository;

    @Override
    public LeadSegment save(LeadSegment leadSegment) {
        return leadSegmentRepository.save(leadSegment);
    }

    @Override
    public Optional<LeadSegment> findById(Long id) {
        return leadSegmentRepository.findById(id);
    }

    @Override
    public List<LeadSegment> findAll() {
        return leadSegmentRepository.findAll();
    }

    @Override
    public Page<LeadSegment> findAllLeadSegment(Pageable pageable) {
        return leadSegmentRepository.findAll(pageable);
    }
}
