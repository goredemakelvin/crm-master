package com.cicosy.crm.service;

import com.cicosy.crm.data.CustomerData;
import com.cicosy.crm.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public abstract class CustomerService extends AbstractService<Customer> {

    public  abstract Customer createCustomer(CustomerData customerData);

    public abstract Page<Customer> findAllCustomers(Pageable pageable);

}
