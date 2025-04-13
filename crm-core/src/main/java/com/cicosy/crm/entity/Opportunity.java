package com.cicosy.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Opportunity  extends BaseEntity{

    private String name;
    @ManyToOne
    private Lead lead;
}
