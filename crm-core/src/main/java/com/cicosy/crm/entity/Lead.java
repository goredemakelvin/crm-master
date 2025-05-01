package com.cicosy.crm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name = "customer_lead")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Lead extends BaseEntity {
    @ManyToOne
    private Customer customer;
    private LocalDate dateCreated;
    @ManyToOne
    private ContactPerson contactPerson;
    @OneToOne
    private Industry industry;

   @ManyToOne
    private BusinessInformation businessInformation;
    @ManyToOne
    private LeadScore leadScore;

    private boolean converted;

    @ManyToOne
    private LeadSegment leadSegment;

    @Enumerated(EnumType.STRING) // Stores as "USER", "ADMIN", etc.
    private LeadStage leadStage;



}
