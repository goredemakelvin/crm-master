package com.cicosy.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Address extends BaseEntity {
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    @ManyToOne
    private Contact contact;


}
