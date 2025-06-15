package com.cicosy.crm.service;

import com.cicosy.crm.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public abstract class CityService extends AbstractService<City> {
    public abstract Page<City> findAllCities(Pageable pageable);
    public abstract Optional<City> findByName(String name);
}
