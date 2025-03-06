package com.cicosy.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Phone extends BaseEntity {

    private String phoneNumber;
    private boolean active;
    @ManyToOne
    private Contact contact;
}
