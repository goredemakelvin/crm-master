package com.cicosy.crm.service;

import com.cicosy.crm.data.EmailTemplate;
import com.cicosy.crm.data.NotificationMessage;
import com.cicosy.crm.entity.Lead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    private String url="http://localhost:8081/send-email-notification";


    @Override
    public void sendEmailNotification(Lead lead, NotificationMessage notificationMessage) {
        EmailTemplate emailTemplate = new EmailTemplate();
        EmailTemplate response = restTemplate.postForObject(url, emailTemplate, EmailTemplate.class);


    }
}
