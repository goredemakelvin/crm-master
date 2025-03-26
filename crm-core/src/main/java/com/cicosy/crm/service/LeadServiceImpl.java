package com.cicosy.crm.service;

import com.cicosy.crm.data.LeadData;
import com.cicosy.crm.entity.*;
import com.cicosy.crm.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LeadServiceImpl extends LeadService {

    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private EmailAddressRepository emailAddressRepository;
    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private BusinessInformationRepository businessInformationRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Lead save(Lead lead) {
        return leadRepository.save(lead);
    }

    @Override
    public Optional<Lead> findById(Long id) {
        return leadRepository.findById(id);
    }

    @Override
    public List<Lead> findAll() {
        return leadRepository.findAll();
    }

    @Override
    public Page<Lead> findAllLeads(Pageable pageable) {
        return leadRepository.findAll(pageable);
    }

    @Override
    public Lead createLead(LeadData leadData) {

        Lead lead = new Lead();
        lead.setDateCreated(LocalDate.now());


        Customer customer = new Customer();
        customer.setFirstName(leadData.getFirstName());
        customer.setLastName(leadData.getLastName());
        customerRepository.save(customer);

        EmailAddress emailAddress= new EmailAddress();
        emailAddress.setEmail(leadData.getEmailAddress());
        emailAddress.setCustomer(customer);
        emailAddressRepository.save(emailAddress);
        customer.getEmailAddress().add(emailAddress);


        Phone phone = new Phone();
        phone.setPhoneNumber(leadData.getPhoneNumber());
        phone.setCustomer(customer);
        phoneRepository.save(phone);
        customer.getPhoneNumbers().add(phone);


        BusinessInformation businessInformation = new BusinessInformation();
        businessInformation.setCompanyName(leadData.getCompany());
        businessInformation.setCustomer(customer);
        businessInformationRepository.save(businessInformation);
        customer.setBusinessInformation(businessInformation);
        customerRepository.save(customer);

        lead.setCustomer(customer);

       return leadRepository.save(lead);
    }

}
