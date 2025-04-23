package com.cicosy.crm.service;

import com.cicosy.crm.entity.NotificationLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class NotificationLogService extends AbstractService<NotificationLog> {
    public abstract Page<NotificationLog> findAllNotifications(Pageable pageable);
}
