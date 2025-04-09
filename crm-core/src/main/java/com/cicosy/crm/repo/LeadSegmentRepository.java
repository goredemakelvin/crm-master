package com.cicosy.crm.repo;

import com.cicosy.crm.entity.LeadSegment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadSegmentRepository extends JpaRepository<LeadSegment, Long> {

    Page<LeadSegment> findAll(Pageable pageable);
}
