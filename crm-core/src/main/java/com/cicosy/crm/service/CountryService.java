package com.cicosy.crm.service;

import com.cicosy.crm.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class CountryService  extends AbstractService<Country> {
    public abstract Page<Country> findAllCountries(Pageable pageable);
}
