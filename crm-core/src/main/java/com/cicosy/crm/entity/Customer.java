package com.cicosy.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Customer extends BaseEntity {

    private String customerNumber;
    @OneToOne
    private Contact contact;
    @OneToOne
    private LoyaltyPoints loyaltyPoints;


}
