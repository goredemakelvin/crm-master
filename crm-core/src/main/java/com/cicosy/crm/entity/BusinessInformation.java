package com.cicosy.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BusinessInformation extends BaseEntity {

    private String companyName;
    @ManyToOne
    private Industry industry;
    @ManyToOne
    private City city;
    @ManyToOne
    private Country country;
    private String jobTitle;
    @ManyToOne
    private ContactPerson accountManager;
    private int companySize;
    private String website;
}
