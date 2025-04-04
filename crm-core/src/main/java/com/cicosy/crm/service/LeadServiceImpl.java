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
    @Autowired
    private LeadScoreService leadScoreService;
    @Autowired
    private ContactPersonRepository contactPersonRepository;


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
        return leadRepository.findByConverted(true,pageable);
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

        leadRepository.save(lead);

        if(leadData.getCompanySize()>0){
            leadScoreService.assignScore(leadData, lead);
        }

       return lead;
    }

    @Override
    public Lead assignLead(Long leadId,Long contactPersonId) {
        Optional<Lead> optionalLead = leadRepository.findById(leadId);
        Optional<ContactPerson> optionalContactPerson = contactPersonRepository.findById(contactPersonId);
        if(optionalLead.isPresent() && optionalContactPerson.isPresent()){
            Lead lead = optionalLead.get();
            lead.setContactPerson(optionalContactPerson.get());
            return leadRepository.save(lead);
        }
        return  null;
    }

    @Override
    public long count() {
        return leadRepository.count();
    }


}
