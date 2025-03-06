package com.cicosy.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Contact extends BaseEntity {

    @OneToMany
    private List<Address> addresses;
    @OneToMany
    private List<Phone> phoneNumbers;
    @OneToMany
    private List<EmailAddress> emailAddress;
    @ManyToOne
    private Customer customer;


}
