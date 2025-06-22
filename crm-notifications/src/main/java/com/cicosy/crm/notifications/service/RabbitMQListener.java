package com.cicosy.crm.notifications.service;

import com.cicosy.crm.notifications.data.EmailTemplate;
import com.cicosy.crm.notifications.model.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQListener {

    @Value("${javainuse.rabbitmq.queue}")
    private String queueName;

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "crm.messages.queue")
    public void onMessage(Notification notification) throws Exception {

        log.info("Consuming Message - " + notification.toString());

        EmailTemplate emailTemplate = new EmailTemplate();
        emailTemplate.setTo(notification.getTo());
        emailTemplate.setSubject(notification.getSubject());
        emailTemplate.setFrom(notification.getFrom());
        emailTemplate.setContent(notification.getContent());
        emailService.sendEmailWithAttachment(emailTemplate);

    }

}