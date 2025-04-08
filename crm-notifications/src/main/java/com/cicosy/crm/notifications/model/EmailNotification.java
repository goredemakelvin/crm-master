package com.cicosy.crm.notifications.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = EmailNotification.class)
public class EmailNotification extends Notification {

    private String subject;
    private String from;
    private String to;

}
