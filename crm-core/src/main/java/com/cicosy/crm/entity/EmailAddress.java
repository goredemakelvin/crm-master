package com.cicosy.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class EmailAddress extends BaseEntity {

    private String email;
    private boolean enabled;
    @ManyToOne
    private Contact contact;
}
