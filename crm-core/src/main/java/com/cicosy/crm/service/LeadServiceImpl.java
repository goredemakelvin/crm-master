package com.cicosy.crm.service;

import com.cicosy.crm.entity.Lead;
import com.cicosy.crm.repo.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeadServiceImpl extends LeadService {

    @Autowired
    private LeadRepository leadRepository;

    @Override
    public Lead save(Lead lead) {
        return leadRepository.save(lead);
    }

    @Override
    public Optional<Lead> findById(Long id) {
        return leadRepository.findById(id);
    }

    @Override
    public List<Lead> findAll() {
        return leadRepository.findAll();
    }

    @Override
    public Page<Lead> findAllLeads(Pageable pageable) {
        return leadRepository.findAll(pageable);
    }
}
