package com.cicosy.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity {

    private String firstName;
    private String lastName;
    private String customerNumber;
    @OneToOne
    private Contact contact;
    @OneToOne
    private LoyaltyPoints loyaltyPoints;

}
