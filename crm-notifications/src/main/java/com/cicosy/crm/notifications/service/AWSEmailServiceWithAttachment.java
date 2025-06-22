package com.cicosy.crm.notifications.service;

import com.cicosy.crm.notifications.config.ConfigReader;
import com.cicosy.crm.notifications.data.EmailTemplate;
import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.Part;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.RawMessage;
import software.amazon.awssdk.services.ses.model.SendRawEmailRequest;
import software.amazon.awssdk.services.ses.model.SendRawEmailResponse;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


@Service
@Primary
public class AWSEmailServiceWithAttachment implements EmailService {

    private final SesClient sesClient;
    private final TemplateEngine templateEngine;
    private final ConfigReader configReader;

    public AWSEmailServiceWithAttachment(SesClient sesClient, TemplateEngine templateEngine, ConfigReader configReader) {
        this.sesClient = sesClient;
        this.templateEngine = templateEngine;
        this.configReader = configReader;
    }







    @Override
    public void sendEmailWithAttachment(EmailTemplate emailTemplate) throws Exception {

        Map<String, Object> variables = new HashMap<>();

        // Prepare the Thymeleaf context
        Context context = new Context();
        variables.put("subject", emailTemplate.getSubject());
        variables.put("content", emailTemplate.getContent());
        variables.put("websiteUrl","https://cicosy.com");
        variables.put("name",emailTemplate.getRecipientName());
        context.setVariables(variables);
        String templateContents = configReader.getTemplateContents();
        String htmlContent = templateEngine.process("email_template", context);




        // Set up the email session
        Properties props = new Properties();
        Session session = Session.getInstance(props);

        // Create the email message
        MimeMessage message = new MimeMessage(session);
        message.setSubject(emailTemplate.getSubject(), "UTF-8");
        message.setFrom(new InternetAddress("info@cicosy.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTemplate.getTo()));

        // Create the message body part
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setContent(htmlContent, "text/html; charset=UTF-8");

        // Create the attachment part
        MimeBodyPart attachmentPart = new MimeBodyPart();
        attachmentPart.setFileName(emailTemplate.getAttachmentName());
        attachmentPart.setContent(emailTemplate.getAttachmentData(), emailTemplate.getContentType());
        attachmentPart.setDisposition(Part.ATTACHMENT);

        // Combine parts
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(textPart);
        multipart.addBodyPart(attachmentPart);

        // Set the full message content
        message.setContent(multipart);

        // Convert the message to bytes
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        message.writeTo(outputStream);

        RawMessage rawMessage = RawMessage.builder()
                .data(SdkBytes.fromByteArray(outputStream.toByteArray()))
                .build();

        // Send the email
        SendRawEmailRequest rawEmailRequest = SendRawEmailRequest.builder()
                .rawMessage(rawMessage)
                .build();

        SendRawEmailResponse sendRawEmailResponse = sesClient.sendRawEmail(rawEmailRequest);
    }

}
