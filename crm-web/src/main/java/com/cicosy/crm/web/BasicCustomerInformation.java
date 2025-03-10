package com.cicosy.crm.web;

import com.cicosy.crm.entity.Customer;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Data
public class BasicCustomerInformation {

    private Customer customer;
    private String firstName;
    private String lastName;
    private String[] phoneNumbers;
    private String[] emailAddresses;
    private int loyaltyPoints;
    private Long id;
    private String customerNumber;


    public BasicCustomerInformation buildBasicCustomerInfomation(Customer customer) {
        this.customer = customer;
        if (this.customer != null) {
            this.firstName = customer.getFirstName();
            this.lastName = customer.getLastName();
            this.id = customer.getId();
            this.loyaltyPoints =getLoyaltyPoints(customer);
            this.emailAddresses = getEmailAddresses(customer);
            this.id=customer.getId();
            this.phoneNumbers=getPhoneNumbers(customer);
            this.customerNumber = customer.getCustomerNumber();
           return this;
        }
       return null;
    }

    private String[] getEmailAddresses(Customer customer) {
        if (!ObjectUtils.isEmpty(customer.getEmailAddress())) {
            List<String> collected = customer.getEmailAddress().stream()
                    .map(object -> Objects.toString(object, null))
                    .collect(Collectors.toList());
            return collected.toArray(new String[collected.size()]);
        } else {
            return new String[]{};
        }
    }

    private int getLoyaltyPoints(Customer customer) {
        if (!ObjectUtils.isEmpty(customer.getLoyaltyPoints())) {
            return customer.getLoyaltyPoints().getPoints();
        } else {
            return 0;
        }
    }
    private String[] getPhoneNumbers(Customer customer) {
        if (!ObjectUtils.isEmpty(customer.getPhoneNumbers())) {
            List<String> collected = customer.getPhoneNumbers().stream()
                    .map(object -> Objects.toString(object, null))
                    .collect(Collectors.toList());
            return collected.toArray(new String[collected.size()]);
        } else {
            return new String[]{};
        }
    }


}
