package com.cicosy.crm.service;

import com.cicosy.crm.data.EmailTemplate;
import com.cicosy.crm.data.NotificationMessage;
import com.cicosy.crm.entity.Lead;

public interface NotificationService {

    public void sendEmailNotification(EmailTemplate emailTemplate);
}
