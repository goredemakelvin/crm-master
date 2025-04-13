package com.cicosy.crm.service;

import com.cicosy.crm.entity.Lead;

public interface LeadNaturingService {

    void sendEmailMessage(Lead lead);
    void sendSmsMessage(Lead lead);

}
