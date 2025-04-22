package com.cicosy.crm.notifications;

import com.cicosy.crm.notifications.config.RabbitMQConfig;
import com.cicosy.crm.notifications.model.Notification;
import com.cicosy.crm.notifications.service.NotificationService;
import com.cicosy.crm.notifications.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrmNotificationsApplication {

    @Autowired
    private RabbitMQSender rabbitMQSender;

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(CrmNotificationsApplication.class, args);

        NotificationService service = applicationContext.getBean(NotificationService.class);
        //service.sendMessage();

    }


}
