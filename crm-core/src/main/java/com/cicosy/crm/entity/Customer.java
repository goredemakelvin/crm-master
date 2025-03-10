package com.cicosy.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private LoyaltyPoints loyaltyPoints;
    @OneToMany
    private List<Address> addresses;
    @OneToMany
    private List<Phone> phoneNumbers;
    @OneToMany
    private List<EmailAddress> emailAddress;

}
