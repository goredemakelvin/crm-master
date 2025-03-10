package com.cicosy.crm.service;

import com.cicosy.crm.entity.BusinessInformation;
import com.cicosy.crm.entity.Customer;
import com.cicosy.crm.repo.BusinessInformationRepository;
import com.cicosy.crm.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessInformationServiceImpl  extends   BusinessInformationService{

    @Autowired
    private BusinessInformationRepository businessInformationRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public BusinessInformation save(BusinessInformation businessInformation) {
        return businessInformationRepository.save(businessInformation);
    }

    @Override
    public Optional<BusinessInformation> findById(Long id) {
        return businessInformationRepository.findById(id);
    }

    @Override
    public List<BusinessInformation> findAll() {
        return businessInformationRepository.findAll();
    }

    @Override
    public void saveBuinessInformation(BusinessInformation businessInformation) {
        Optional<Customer> optionalCustomer = customerRepository.findById(businessInformation.getCustomerId());
        businessInformationRepository.save(businessInformation);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setBusinessInformation(businessInformation);
            customerRepository.save(customer);
        }
    }
}
