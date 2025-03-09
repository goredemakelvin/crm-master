package com.cicosy.crm.service;

import com.cicosy.crm.data.CustomerData;
import com.cicosy.crm.entity.Customer;
import com.cicosy.crm.repo.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImp extends CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer save(Customer t) {
        return customerRepository.save(t);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public void createCustomer(CustomerData customerData) {
        log.debug("-----Creating Customer--------: " + customerData.toString());
        Customer customer = Customer.builder().firstName(customerData.getFirstName()).lastName(customerData.getLastName()).build();
        customerRepository.save(customer);
    }
}
