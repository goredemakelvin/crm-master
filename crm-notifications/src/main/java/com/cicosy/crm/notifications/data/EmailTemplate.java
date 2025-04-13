package com.cicosy.crm.notifications.data;

import lombok.Data;

@Data
public class EmailTemplate {

    private String subject;
    private String content;
    private String to;
    private String from;

}
