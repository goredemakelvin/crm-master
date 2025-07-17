package com.cicosy.crm.service;

import com.cicosy.crm.data.CustomerData;
import com.cicosy.crm.entity.*;
import com.cicosy.crm.repo.BusinessInformationRepository;
import com.cicosy.crm.repo.CustomerRepository;
import com.cicosy.crm.repo.EmailAddressRepository;
import com.cicosy.crm.repo.PhoneRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
@Slf4j
public class CustomerServiceImp extends CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmailAddressRepository emailAddressRepository;
    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private CityService cityService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private BusinessInformationRepository businessInformationRepository;


    @Override
    public Customer save(Customer t) {
        return customerRepository.save(t);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer createCustomer(CustomerData customerData) {

        try {
            Optional<Customer> optionalCustomer = customerRepository.findByCustomerId(customerData.getCustomerID());
            if (optionalCustomer.isPresent()) {
                log.warn("Customer with ID {} already exists", customerData.getCustomerID());
                return optionalCustomer.get();
            }
            Customer customer = new Customer();
            customer.setCustomerId(customerData.getCustomerID());
            customer.setFirstName(customerData.getFirstName());
            customer.setLastName(customerData.getLastName());
            customer = customerRepository.save(customer);

            Phone phone = new Phone();
            phone.setPhoneNumber1(customerData.getPhoneNumber1());
            phone.setPhoneNumber2(customerData.getPhoneNumber2());
            phone.setCustomer(customer);
            phoneRepository.save(phone);
            customer.getPhoneNumbers().add(phone);

            EmailAddress emailAddress = new EmailAddress();
            emailAddress.setEmail(customerData.getEmailAddress());
            emailAddress.setCustomer(customer);
            emailAddressRepository.save(emailAddress);
            customer.getEmailAddress().add(emailAddress);

            customer.setCustomerNumber(getCustomerNumber());
            customerRepository.save(customer);

            Optional<City> city = cityService.findByName(customerData.getCity());
            if (city.isPresent()) {
                customer.setCity(city.get());
            } else {
                City city1 = new City();
                city1.setName(customerData.getCity());
                cityService.save(city1);
                customer.setCity(city1);
            }
            Optional<Country> country = countryService.findByName(customerData.getCountry());
            if (country.isPresent()) {
                customer.setCountry(country.get());
            } else {
                Country country1 = new Country();
                country1.setName(customerData.getCountry());
                countryService.save(country1);
                customer.setCountry(country1);
            }
            customer.setSubscriptionDate(customerData.getSubscriptionDate());
            customer.setWebsite(customerData.getWebsite());
            customer.setConverted(true);
            customerRepository.save(customer);


            Optional<BusinessInformation> optionalCompany = businessInformationRepository.findByCompanyName(customerData.getCompanyName());
            if (optionalCustomer.isEmpty()) {
                log.warn("Business information with company name {} already exists", customerData.getCompanyName());
                BusinessInformation businessInformation = new BusinessInformation();
                businessInformation.setCompanyName(customerData.getCompanyName());
                businessInformation.setCity(customer.getCity());
                businessInformation.setCountry(customer.getCountry());
                businessInformation.setWebsite(customerData.getWebsite());
                businessInformationRepository.save(businessInformation);
                customer.setBusinessInformation(businessInformation);
                customerRepository.save(customer);
            } else {

                customer.setBusinessInformation(optionalCompany.get());
                customerRepository.save(customer);
            }


            log.info("Customer created successfully with ID: {}", customer.getId());
            return customer;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error creating customer: {}", e.getMessage());
            throw new RuntimeException("Failed to create customer", e);
        }

    }

    @Override
    public Page<Customer> findAllCustomers(Pageable pageable) {
        return customerRepository.findByConverted(true, pageable);
    }

    @Override
    public long countByConverted(boolean converted) {
        return customerRepository.countByConverted(converted);
    }

    private String getCustomerNumber() {
        int i = new Random().nextInt(1000000) + 1;
        return String.valueOf(i);
    }


}
