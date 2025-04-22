package com.cicosy.crm.notifications.service;

import com.cicosy.crm.notifications.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private RabbitMQSender rabbitMQSender;

    public void sendMessage() {
        Notification notification = new Notification();
        notification.setTitle("Notification Title");
        notification.setContent("Notification Message");
        rabbitMQSender.send(notification);
    }
}
