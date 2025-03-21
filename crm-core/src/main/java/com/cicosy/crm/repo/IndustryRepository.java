package com.cicosy.crm.repo;

import com.cicosy.crm.entity.Industry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndustryRepository extends JpaRepository<Industry, Long> {
    Page<Industry> findAll(Pageable pageable);
}
