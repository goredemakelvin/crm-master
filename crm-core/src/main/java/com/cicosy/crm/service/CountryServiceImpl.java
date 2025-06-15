package com.cicosy.crm.service;

import com.cicosy.crm.entity.Country;
import com.cicosy.crm.repo.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl extends CountryService{
    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Page<Country> findAllCountries(Pageable pageable) {
        return countryRepository.findAll(pageable);
    }

    @Override
    public Optional<Country> findByName(String name) {
        return countryRepository.findByNameLike(name);

    }
}
