package com.cicosy.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Lead extends BaseEntity {
    @OneToOne
    private Customer customer;
    private LocalDate dateCreated;
    @OneToOne
    private ContactPerson contactPerson;
    private String jobTitle;
    private int companySize;
    @OneToOne
    private Industry industry;


}
