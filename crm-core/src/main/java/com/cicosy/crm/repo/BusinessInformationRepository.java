package com.cicosy.crm.repo;

import com.cicosy.crm.entity.BusinessInformation;
import com.cicosy.crm.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusinessInformationRepository extends JpaRepository<BusinessInformation, Long> {

    Optional<BusinessInformation> findByCustomer(Customer customer);

    Page<BusinessInformation> findAll(Pageable pageable);

}
