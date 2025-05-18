package com.cicosy.crm.notifications.data;

import lombok.Data;

@Data
public class EmailTemplate {

    private String title;
    private String from;
    private String to;
    private String subject;
    private String attachment;
    private String content;
    private int id;
    private byte[] attachmentData;
    private String attachmentName;
    private String contentType;

}
