package com.cicosy.crm.service;

import com.cicosy.crm.entity.Lead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeadNaturingServiceImpl  implements LeadNaturingService {

    @Autowired
    private NotificationService notificationService;


    @Override
    public void sendEmailMessage(Lead lead) {
        //notificationService.sendEmailNotification(lead, notificationMessage);
    }

    @Override
    public void sendSmsMessage(Lead lead) {

    }
}
