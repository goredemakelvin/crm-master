package com.cicosy.crm.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContactPerson extends BaseEntity {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;


}
