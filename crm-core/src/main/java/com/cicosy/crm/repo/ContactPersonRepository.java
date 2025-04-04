package com.cicosy.crm.repo;

import com.cicosy.crm.entity.ContactPerson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactPersonRepository extends JpaRepository<ContactPerson, Long> {

    Page<ContactPerson> findAll(Pageable pageable);
}
