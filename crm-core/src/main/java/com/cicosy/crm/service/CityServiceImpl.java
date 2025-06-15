package com.cicosy.crm.service;

import com.cicosy.crm.entity.City;
import com.cicosy.crm.repo.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl extends CityService{

    @Autowired
    private CityRepository cityRepository;

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public Page<City> findAllCities(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }

    @Override
    public Optional<City> findByName(String name) {
        return  cityRepository.findByNameLike(name);
    }
}
