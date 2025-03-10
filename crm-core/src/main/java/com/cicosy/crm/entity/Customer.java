package com.cicosy.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer extends BaseEntity {

    private String firstName;
    private String lastName;
    private String customerNumber;
    @OneToOne
    private LoyaltyPoints loyaltyPoints;
    @OneToMany
    private List<Address> addresses = new ArrayList<>();
    @OneToMany
    private List<Phone> phoneNumbers= new ArrayList<>();
    @OneToMany
    private List<EmailAddress> emailAddress =new ArrayList<>();
    private String city;
    private String country;

}
