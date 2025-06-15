package com.cicosy.crm.repo;

import com.cicosy.crm.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Page<City> findAll(Pageable pageable);
    Optional<City> findByNameLike(String name);
}
