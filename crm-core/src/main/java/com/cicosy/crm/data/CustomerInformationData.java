package com.cicosy.crm.data;

import com.cicosy.crm.entity.BusinessInformation;
import com.cicosy.crm.entity.Customer;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;

@Data
public class CustomerInformationData {

    private Customer customer;
    private String firstName;
    private String lastName;
    private String phoneNumbers;
    private String emailAddresses;
    private int loyaltyPoints;
    private Long id;
    private String customerNumber;
    private BusinessInformationData businessInformation;
    private LocalDate subscriptionDate;
    private String website;




    public CustomerInformationData getCustomerInformation(Customer customer) {
        this.customer = customer;
        if (this.customer != null) {
            this.firstName = customer.getFirstName();
            this.lastName = customer.getLastName();
            this.id = customer.getId();
            this.emailAddresses = getEmailAddresses(customer);
            this.id = customer.getId();
            this.phoneNumbers = getPhoneNumbers(customer);
            this.customerNumber = customer.getCustomerId();
            this.website=customer.getWebsite();
            this.subscriptionDate = customer.getSubscriptionDate();
            return this;

        }
        return null;
    }

    private String getEmailAddresses(Customer customer) {
        if (!ObjectUtils.isEmpty(customer.getEmailAddress())) {
            StringBuilder builder = new StringBuilder();
            builder.append("[");
            customer.getEmailAddress().forEach(item ->
                    builder.append(item.getEmail()).append(","));
            builder.append("]");
            return builder.toString();
        } else {
            return "";
        }
    }


    private String getPhoneNumbers(Customer customer) {
        if (!ObjectUtils.isEmpty(customer.getPhoneNumbers())) {
            StringBuilder builder = new StringBuilder();
            builder.append("[");
            customer.getPhoneNumbers().forEach(item ->
                    builder.append(item.getPhoneNumber1()).append(","));
            builder.append("]");
            return builder.toString();
        } else {
            return "";
        }
    }
}
