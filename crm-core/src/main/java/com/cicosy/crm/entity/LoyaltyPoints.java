package com.cicosy.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class LoyaltyPoints extends BaseEntity {

    private int points;
    @ManyToOne
    private Customer customer;


}
