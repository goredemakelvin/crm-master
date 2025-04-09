package com.cicosy.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class LeadSegment  extends BaseEntity{

    @OneToMany
    private List<Lead> leads;
    private boolean active;
    private String name;

}
