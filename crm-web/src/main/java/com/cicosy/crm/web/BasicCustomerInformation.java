package com.cicosy.crm.web;

import com.cicosy.crm.entity.Customer;
import lombok.Data;
import org.springframework.util.ObjectUtils;

@Data
public class BasicCustomerInformation {

    private Customer customer;
    private String firstName;
    private String lastName;
    private String phoneNumbers;
    private String emailAddresses;
    private int loyaltyPoints;
    private Long id;
    private String customerNumber;


    public BasicCustomerInformation buildBasicCustomerInfomation(Customer customer) {
        this.customer = customer;
        if (this.customer != null) {
            this.firstName = customer.getFirstName();
            this.lastName = customer.getLastName();
            this.id = customer.getId();
            this.loyaltyPoints = getLoyaltyPoints(customer);
            this.emailAddresses = getEmailAddresses(customer);
            this.id = customer.getId();
            this.phoneNumbers = getPhoneNumbers(customer);
            this.customerNumber = customer.getCustomerNumber();
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

    private int getLoyaltyPoints(Customer customer) {
//        if (!ObjectUtils.isEmpty(customer.getLoyaltyPoints())) {
//            return customer.getLoyaltyPoints().getPoints();
//        } else {
            return 0;
      //  }
    }

    private String getPhoneNumbers(Customer customer) {
        if (!ObjectUtils.isEmpty(customer.getPhoneNumbers())) {
            StringBuilder builder = new StringBuilder();
            builder.append("[");
            customer.getPhoneNumbers().forEach(item ->
                    builder.append(item.getPhoneNumber()).append(","));
            builder.append("]");
            return builder.toString();
        } else {
            return "";
        }
    }
}
