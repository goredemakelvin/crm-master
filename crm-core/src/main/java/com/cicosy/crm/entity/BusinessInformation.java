package com.cicosy.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BusinessInformation  extends BaseEntity{

    private String companyName;
    @OneToOne
    private Industry industry;
    @OneToOne
    private City city;
    @OneToOne
    private Country country;

    private String jobTitle;

    @OneToOne
    private ContactPerson accountManager;
    @OneToOne
    private Customer customer;

    private int companySize;

}
