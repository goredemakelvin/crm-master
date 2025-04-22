package com.cicosy.crm.notifications.controller;

import com.cicosy.crm.notifications.data.EmailTemplate;
import com.cicosy.crm.notifications.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @PostMapping("/send-email-notification")
    public ResponseEntity sendEmail(@RequestBody EmailTemplate emailTemplate) {
        rabbitMQSender.sendEmailNotification(emailTemplate);
        return ResponseEntity.ok(emailTemplate);
    }
}
