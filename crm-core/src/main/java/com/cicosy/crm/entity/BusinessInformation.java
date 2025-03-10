package com.cicosy.crm.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BusinessInformation  extends BaseEntity{

    private String companyName;
    private String industry;
    private String jobTitle;
    private String accountManager;
    private Long customerId;

}
