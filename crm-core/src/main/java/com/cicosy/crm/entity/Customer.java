package com.cicosy.crm.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
    @OneToMany
    private List<Address> addresses = new ArrayList<>();
    @OneToMany
    private List<Phone> phoneNumbers= new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER)
    private List<EmailAddress> emailAddress =new ArrayList<>();
    @ManyToOne
    private City city;
    @ManyToOne
    private Country country;
    @ManyToOne
    private BusinessInformation businessInformation;
    private boolean converted;
    private LocalDate subscriptionDate;
    private String website;
    private String customerId;


}
