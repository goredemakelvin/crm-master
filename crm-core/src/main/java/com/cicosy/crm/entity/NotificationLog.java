package com.cicosy.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotificationLog  extends BaseEntity{

    @ManyToOne
    private PromotionMessage promotionMessage;
    @ManyToOne
    private Lead lead;
    @ManyToOne
    private Account account;
    @ManyToOne
    private Opportunity opportunity;
    @ManyToOne
    private Contact contact;

}
