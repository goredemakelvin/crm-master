package com.cicosy.crm.service;

import com.cicosy.crm.entity.Industry;
import com.cicosy.crm.entity.Lead;
import com.cicosy.crm.repo.IndustryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndustryServiceImpl extends IndustryService {

    @Autowired
    private IndustryRepository industryRepository;


    @Override
    public Industry save(Industry industry) {
        return industryRepository.save(industry);
    }

    @Override
    public Optional<Industry> findById(Long id) {
        return industryRepository.findById(id);
    }

    @Override
    public List<Industry> findAll() {
        return industryRepository.findAll();
    }


    @Override
    public Page<Industry> findAllIndustries(Pageable pageable) {
        return industryRepository.findAll(pageable);
    }
}
