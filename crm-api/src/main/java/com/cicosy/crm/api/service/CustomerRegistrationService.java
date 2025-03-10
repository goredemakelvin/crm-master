package com.cicosy.crm.api.service;

import com.cicosy.crm.api.data.CustomerRegistrationData;
import com.cicosy.crm.entity.Customer;

public interface CustomerRegistrationService {

    void registerCustomer(CustomerRegistrationData  customerRegistrationData);
}
