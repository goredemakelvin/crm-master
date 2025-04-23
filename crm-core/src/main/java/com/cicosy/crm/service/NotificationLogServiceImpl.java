package com.cicosy.crm.service;

import com.cicosy.crm.entity.NotificationLog;
import com.cicosy.crm.repo.NotificationLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public  class NotificationLogServiceImpl extends NotificationLogService {

    @Autowired
    private NotificationLogRepository notificationLogRepository;

    @Override
    public NotificationLog save(NotificationLog NotificationLog) {
        return notificationLogRepository.save(NotificationLog);
    }

    @Override
    public Optional<NotificationLog> findById(Long id) {
        return notificationLogRepository.findById(id);
    }

    @Override
    public List<NotificationLog> findAll() {
        return notificationLogRepository.findAll();
    }

    @Override
    public Page<NotificationLog> findAllNotifications(Pageable pageable) {
        return notificationLogRepository.findAll(pageable);
    }
}
