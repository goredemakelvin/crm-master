package com.cicosy.crm.service;

import com.cicosy.crm.entity.*;
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
    public void convertLead(Lead lead) {

        if(lead.getLeadStage().equals(LeadStage.ACCOUNT)) {
            Account account = new Account();
            account.setAccountNumber("1L");
            if(lead.getCustomer() != null) {
                account.setName(lead.getCustomer().getFirstName() + " " + lead.getCustomer().getLastName());
            }
            account.setLead(lead);
            accountService.save(account);
        }

        if (lead.getLeadStage().equals(LeadStage.CONTACT)) {
            Contact contact = new Contact();
            contact.setLead(lead);
            if(lead.getCustomer() != null) {
                contact.setName(lead.getCustomer().getFirstName() + " " + lead.getCustomer().getLastName());
            }
            contactService.save(contact);
        }

        if(lead.getLeadStage().equals(LeadStage.OPPORTUNITY)) {
            Opportunity opportunity = new Opportunity();
            opportunity.setLead(lead);
            if(lead.getCustomer() != null) {
                opportunity.setName(lead.getCustomer().getFirstName() + " " + lead.getCustomer().getLastName());
            }
           opportunityService.save(opportunity);
        }
    }
}
