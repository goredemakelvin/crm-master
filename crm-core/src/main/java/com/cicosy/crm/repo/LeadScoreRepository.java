package com.cicosy.crm.repo;

import com.cicosy.crm.entity.LeadScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadScoreRepository extends JpaRepository<LeadScore, Long> {

}
