package com.cicosy.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoyaltyPoints extends BaseEntity {

    private int points;
    @ManyToOne
    private Customer customer;


}
