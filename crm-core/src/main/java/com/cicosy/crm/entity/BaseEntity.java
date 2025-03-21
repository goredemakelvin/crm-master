package com.cicosy.crm.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
}
