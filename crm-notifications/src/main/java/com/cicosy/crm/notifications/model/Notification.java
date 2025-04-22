package com.cicosy.crm.notifications.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Notification implements Serializable {
    private String title;
    private String from;
    private String to;
    private String subject;
    private String attachment;
    private String content;
    private int id;
}
