package com.cicosy.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Phone extends BaseEntity {

    private String phoneNumber1;
    private String phoneNumber2;
    private boolean active;
    @ManyToOne
    private Customer customer;
}
