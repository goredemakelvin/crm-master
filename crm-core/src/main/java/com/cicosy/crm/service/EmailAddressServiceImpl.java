package com.cicosy.crm.service;

import com.cicosy.crm.entity.EmailAddress;
import com.cicosy.crm.repo.EmailAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailAddressServiceImpl extends EmailAddressService {

    @Autowired
    private EmailAddressRepository emailAddressRepository;

    @Override
    public EmailAddress save(EmailAddress emailAddress) {
        return emailAddressRepository.save(emailAddress);
    }

    @Override
    public Optional<EmailAddress> findById(Long id) {
        return emailAddressRepository.findById(id);
    }

    @Override
    public List<EmailAddress> findAll() {
        return emailAddressRepository.findAll();
    }
}
