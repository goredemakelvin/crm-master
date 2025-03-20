package com.cicosy.crm.service;

import com.cicosy.crm.entity.Industry;
import com.cicosy.crm.repo.IndustryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndustryServiceImpl extends IndustryService {

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
}
