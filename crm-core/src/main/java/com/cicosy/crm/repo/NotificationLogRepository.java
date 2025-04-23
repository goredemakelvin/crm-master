package com.cicosy.crm.repo;

import com.cicosy.crm.entity.NotificationLog;
import com.cicosy.crm.entity.Opportunity;
import com.cicosy.crm.entity.Phone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationLogRepository extends JpaRepository<NotificationLog, Long> {
    Page<NotificationLog> findAll(Pageable pageable);
}
