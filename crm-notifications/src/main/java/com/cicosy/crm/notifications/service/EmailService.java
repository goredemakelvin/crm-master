package com.cicosy.crm.notifications.service;

import com.cicosy.crm.notifications.data.EmailTemplate;

public interface EmailService {

    

    void sendEmailWithAttachment(EmailTemplate emailTemplate) throws Exception;
}
