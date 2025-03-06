package com.cicosy.crm.service;

import java.util.List;
import java.util.Optional;

public abstract class AbstractService<T> {
    public abstract T save(T t);

    public abstract Optional<T> findById(Long id);

    public abstract List<T> findAll();
}
