package com.cicosy.crm.notifications;

import com.cicosy.crm.notifications.config.ConfigReader;
import com.cicosy.crm.notifications.data.EmailTemplate;
import com.cicosy.crm.notifications.service.EmailService;
import com.cicosy.crm.notifications.service.NotificationService;
import com.cicosy.crm.notifications.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CrmNotificationsApplication {

    @Autowired
    private RabbitMQSender rabbitMQSender;
    @Autowired
    private ConfigReader configReader;

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(CrmNotificationsApplication.class, args);

        NotificationService service = applicationContext.getBean(NotificationService.class);
        //service.sendMessage();


        ConfigReader configReader1 = applicationContext.getBean(ConfigReader.class);

        EmailTemplate emailTemplate = new EmailTemplate();
        emailTemplate.setTo("zengeniedd@gmail.com");
        emailTemplate.setSubject("Test Email");
        emailTemplate.setAttachment("Test Attachment");
        emailTemplate.setAttachmentName("Test Attachment");
        emailTemplate.setContentType("application/pdf");
        emailTemplate.setAttachmentData(configReader1.getAttachmentContents());
        emailTemplate.setContent(configReader1.getTemplateContents());

        EmailService emailService = applicationContext.getBean(EmailService.class);
        emailService.sendSimpleEmailV2(emailTemplate);
        //emailService.sendEmailWithAttachmentV2(emailTemplate);

    }


}
