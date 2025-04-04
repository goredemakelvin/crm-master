package com.cicosy.crm.data;

import com.cicosy.crm.entity.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class LeadData {

    private String fullName;
    private Long id;
    private String industry;
    private String emailAddress;
    @NotBlank(message = "Please enter your Phone Number")
    private String phoneNumber;
    @NotBlank(message = "Please enter your Company Name")
    private String company;
    private String jobTitle;
    private String city;
    private String country;
    private String leadSource;
    private String utmParameters;
    private String referralURL;
    private String[] pagesVisited;
    private int timeSpentOnSite;
    private String[] downloadHistory;
    private String preferedContact;
    private String enquiry;
    private String[] webformResponses;
    private boolean optInConsent;
    @NotBlank(message = "Please enter your FirstName")
    private String firstName;
    @NotBlank(message = "Please enter your LastName")
    private String lastName;
    @NotBlank(message = "Please enter your company size")
    private int companySize;
    private long leadScore;
    private String contactPerson;


    public LeadData getLeadData(Lead lead) {
        LeadData leadData = new LeadData();
        leadData.setId(lead.getId());
        if (lead.getCustomer() != null) {
            Customer customer = lead.getCustomer();
            String firstName = customer.getFirstName();
            String lastName = customer.getLastName();
            String fullName = firstName + " " + lastName;
            leadData.setFullName(fullName);
            if (customer.getBusinessInformation() != null) {
                BusinessInformation businessInformation = customer.getBusinessInformation();
                if (businessInformation.getIndustry() != null) {
                    leadData.setIndustry(businessInformation.getIndustry().getName());
                }
                if (businessInformation.getCity() != null) {
                    leadData.setCity(businessInformation.getCity().getName());
                }
            }
            if (customer.getEmailAddress() != null) {
                List<EmailAddress> emailAddresses = customer.getEmailAddress();
                String[] emails = emailAddresses.stream().map(String::valueOf).toArray(String[]::new);
                int i = 0;
                for (EmailAddress e : emailAddresses) {
                    emails[i++] = e.getEmail();
                }
                leadData.setEmailAddress(Arrays.toString(emails));
            }

            if (customer.getPhoneNumbers() != null) {
                List<Phone> phones = customer.getPhoneNumbers();
                String[] phoneNumbers = phones.stream().map(String::valueOf).toArray(String[]::new);
                int i = 0;
                for (Phone e : phones) {
                    phoneNumbers[i++] = e.getPhoneNumber();
                }
                leadData.setPhoneNumber(Arrays.toString(phoneNumbers));
            }
            if (customer.getCountry() != null) {
                leadData.setCountry(customer.getCountry().getName());

            }
            if(lead.getLeadScore() != null) {
                leadData.setLeadScore(lead.getLeadScore().getScore());
            }

            if(lead.getContactPerson() != null) {
                String contactPerson= lead.getContactPerson().getFirstName()+ " "+ lead.getContactPerson().getLastName();
                leadData.setContactPerson(contactPerson);
            }


        }
        return leadData;
    }
}
