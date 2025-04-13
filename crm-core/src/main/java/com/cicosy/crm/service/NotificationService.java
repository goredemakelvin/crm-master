package com.cicosy.crm.service;

import com.cicosy.crm.entity.Lead;

public interface NotificationService {

    public void sendEmailNotification(Lead lead);
}
