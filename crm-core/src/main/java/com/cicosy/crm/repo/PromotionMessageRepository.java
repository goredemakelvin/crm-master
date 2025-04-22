package com.cicosy.crm.repo;

import com.cicosy.crm.entity.Account;
import com.cicosy.crm.entity.PromotionMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionMessageRepository extends JpaRepository<PromotionMessage, Long> {
    Page<PromotionMessage> findAll(Pageable pageable);
}
