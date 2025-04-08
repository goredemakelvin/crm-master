package com.cicosy.crm.notifications.model;

import lombok.Data;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Notification.class)
public class Notification implements Serializable {
    private String message;
    private String title;
    private int id;
}
