package com.cicosy.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Account extends BaseEntity {

    private String name;
    private String accountNumber;
    @ManyToOne
    private Lead lead;
}
