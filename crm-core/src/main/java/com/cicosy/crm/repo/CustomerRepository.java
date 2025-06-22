package com.cicosy.crm.repo;

import com.cicosy.crm.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.concurrent.CyclicBarrier;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Page<Customer> findByConverted(boolean converted,Pageable pageable);
     long countByConverted(boolean converted);
     Optional<Customer> findByCustomerId(String customerId);
}
