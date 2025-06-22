package com.cicosy.crm.notifications.service;

import com.cicosy.crm.notifications.data.EmailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

@Service
//@Primary
public class AWSSimpleEmailServiceImpl implements EmailService {


    @Autowired
    private SesClient sesClient;

    public void sendEmailWithAttachment(EmailTemplate details) {
        Destination destination = Destination.builder()
                .toAddresses(details.getTo())
                .build();

        Content content = Content.builder()
                .data(details.getContent())
                .build();

        Content subj = Content.builder()
                .data(details.getSubject())
                .build();

        Body emailBody = Body.builder()
                .text(content)
                .build();

        Message message = Message.builder()
                .subject(subj)
                .body(emailBody)
                .build();

        SendEmailRequest emailRequest = SendEmailRequest.builder()
                .destination(destination)
                .message(message)
                .source("info@cicosy.com")
                .build();

        SendEmailResponse sendEmailResponse = sesClient.sendEmail(emailRequest);
        //return sendEmailResponse.toString();
    }


}

