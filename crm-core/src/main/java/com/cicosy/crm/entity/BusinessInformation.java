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
    private String jobTitle;
    private String accountManager;
    private Long customerId;
    private int companySize;

}
