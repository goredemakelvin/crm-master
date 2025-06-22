package com.cicosy.crm.notifications.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Component
public class ConfigReader {

    @Value("classpath:attachment.pdf")
    private Resource resource;

    @Value("classpath:templates/email_template.html")
    private Resource emailTemplateResource;

    public byte[] getAttachmentContents() throws Exception {
        return Files.readAllBytes(resource.getFile().toPath());
    }

    public String getTemplateContents() throws Exception {
        return Files.readString(emailTemplateResource.getFile().toPath());
    }

    public String loadHtmlTemplate() throws IOException {
        return StreamUtils.copyToString(emailTemplateResource.getInputStream(), StandardCharsets.UTF_8);
    }
}
