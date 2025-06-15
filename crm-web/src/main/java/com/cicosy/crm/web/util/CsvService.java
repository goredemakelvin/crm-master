package com.cicosy.crm.web.util;

import com.cicosy.crm.data.CustomerData;
import com.cicosy.crm.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class CsvService {

    @Autowired
    private CustomerService customerService;

    public void parseCustomerData(MultipartFile file) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // skip header
                    continue;
                }
                String[] parts = line.split(",", -1); // limit -1 to avoid missing trailing empty fields
                if (parts.length >= 10) {
                    CustomerData customerData = new CustomerData();
                    customerData.setCustomerID(parts[0].trim());
                    customerData.setFirstName(parts[1].trim());
                    customerData.setLastName(parts[2].trim());
                    customerData.setCompanyName(parts[3].trim());
                    customerData.setCity(parts[4].trim());
                    customerData.setCountry(parts[5].trim());
                    customerData.setPhonenumber1(parts[6].trim());
                    customerData.setPhonenumber2(parts[7].trim());
                    customerData.setEmailAddress(parts[8].trim());
                    String subscriptionDate = parts[9].trim();
                    String website = parts[10].trim();
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
                        LocalDate date = LocalDate.parse(subscriptionDate, formatter);
                        customerData.setSubscriptionDate(date);
                    }catch (Exception e) {
                        log.error("Invalid date format for subscription date: " + subscriptionDate, e);
                        continue; // Skip this record if date parsing fails
                    }
                    customerData.setWebsite(website);// Assuming current date for subscription
                    customerService.createCustomer(customerData);

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error reading CSV file: " + e.getMessage(), e);

        }


    }
}
