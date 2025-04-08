package com.cicosy.crm.notifications.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = SmsNotification.class)
public class SmsNotification extends Notification {
    private String message;
    private String mobile;

}
