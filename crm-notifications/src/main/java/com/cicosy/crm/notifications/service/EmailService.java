package com.cicosy.crm.notifications.service;

import com.cicosy.crm.notifications.data.EmailTemplate;

public interface EmailService {


    String sendSimpleMail(EmailTemplate details);

    String sendEmailWithAttachment(EmailTemplate details);

    String sendSimpleEmailV2(EmailTemplate details);

    public void sendEmailWithAttachmentV2(EmailTemplate emailTemplate) throws Exception;
}
