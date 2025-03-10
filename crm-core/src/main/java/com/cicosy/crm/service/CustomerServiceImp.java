package com.cicosy.crm.service;

import com.cicosy.crm.data.CustomerData;
import com.cicosy.crm.entity.*;
import com.cicosy.crm.repo.CustomerRepository;
import com.cicosy.crm.repo.EmailAddressRepository;
import com.cicosy.crm.repo.LoyaltyPointsRepository;
import com.cicosy.crm.repo.PhoneRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
public class CustomerServiceImp extends CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmailAddressRepository emailAddressRepository;
    @Autowired
    private PhoneRepository phoneRepository;;
    @Autowired
    private LoyaltyPointsRepository loyaltyPointsRepository;


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
    public void createCustomer(CustomerData customerData) {
        log.debug("======Creating Customer =======: " + customerData.toString());

        Customer customer = new Customer();
        customer.setFirstName(customerData.getFirstName());
        customer.setLastName(customerData.getLastName());
        customerRepository.save(customer);

        Phone phone = new Phone();
        phone.setPhoneNumber(customerData.getPhoneNumber());
        phone.setCustomer(customer);
        phoneRepository.save(phone);
        customer.getPhoneNumbers().add(phone);

        EmailAddress emailAddress= new EmailAddress();
        emailAddress.setEmail(customerData.getEmailAddress());
        emailAddress.setCustomer(customer);
        emailAddressRepository.save(emailAddress);
        customer.getEmailAddress().add(emailAddress);

        customer.setCustomerNumber(getCustomerNumber());

        LoyaltyPoints loyaltyPoints= new LoyaltyPoints();
        loyaltyPoints.setPoints(10);
        loyaltyPoints.setCustomer(customer);
        loyaltyPointsRepository.save(loyaltyPoints);
        customer.setLoyaltyPoints(loyaltyPoints);

        customerRepository.save(customer);
        loyaltyPoints.setCustomer(customer);
        log.debug("======Customer Creation Complete =======: ");

    }

    @Override
    public Page<Customer> findAllCustomers(Pageable pageable) {
        return  customerRepository.findAll(pageable);
    }

    private String getCustomerNumber() {
        int i = new Random().nextInt(1000000) + 1;
        return String.valueOf(i);
    }
}
