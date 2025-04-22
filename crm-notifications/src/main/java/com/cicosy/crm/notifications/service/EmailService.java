package com.cicosy.crm.notifications.service;

import com.cicosy.crm.notifications.data.EmailTemplate;

public interface EmailService {


    String sendSimpleMail(EmailTemplate details);

    // Method
    // To send an email with attachment
    String sendMailWithAttachment(EmailTemplate details);
}
