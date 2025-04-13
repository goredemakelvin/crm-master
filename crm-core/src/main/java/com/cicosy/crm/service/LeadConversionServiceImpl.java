package com.cicosy.crm.service;

import com.cicosy.crm.entity.Account;
import com.cicosy.crm.entity.Contact;
import com.cicosy.crm.entity.Lead;
import com.cicosy.crm.entity.Opportunity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeadConversionServiceImpl  implements LeadConversionService {

    @Autowired
    private LeadService leadService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private OpportunityService opportunityService;

    @Override
    public void convertLead(Lead lead,int stage) {

        if(stage == 1) {
            Account account = new Account();
            account.setAccountNumber("1L");
            if(lead.getCustomer() != null) {
                account.setName(lead.getCustomer().getFirstName() + " " + lead.getCustomer().getLastName());
            }
            account.setLead(lead);
            accountService.save(account);
        }

        if (stage == 2) {
            Contact contact = new Contact();
            contact.setLead(lead);
            if(lead.getCustomer() != null) {
                contact.setName(lead.getCustomer().getFirstName() + " " + lead.getCustomer().getLastName());
            }
            contactService.save(contact);
        }

        if(stage == 3) {
            Opportunity opportunity = new Opportunity();
            opportunity.setLead(lead);
            if(lead.getCustomer() != null) {
                opportunity.setName(lead.getCustomer().getFirstName() + " " + lead.getCustomer().getLastName());
            }
           opportunityService.save(opportunity);
        }
    }
}
