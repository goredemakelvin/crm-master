package com.cicosy.crm.service;

import com.cicosy.crm.data.CustomerData;
import com.cicosy.crm.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public abstract class CustomerService extends AbstractService<Customer> {

    public  abstract void createCustomer(CustomerData customerData);

}
