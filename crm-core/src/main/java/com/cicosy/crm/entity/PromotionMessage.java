package com.cicosy.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class PromotionMessage  extends BaseEntity{

    @ManyToOne
    private LeadSegment leadSegment;
    private String  text;
    private String subject;
    private int scheduleDay;
}
