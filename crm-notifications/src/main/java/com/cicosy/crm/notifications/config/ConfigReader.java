package com.cicosy.crm.notifications.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.nio.file.Files;

@Component
public class ConfigReader {

    @Value("classpath:attachment.pdf")
    private Resource resource;

    @Value("classpath:email_template.html")
    private Resource emailTemplateResource;

    public byte[] getAttachmentContents() throws Exception {
        return Files.readAllBytes(resource.getFile().toPath());
    }

    public String getTemplateContents() throws Exception {
        return Files.readString(emailTemplateResource.getFile().toPath());
    }
}
