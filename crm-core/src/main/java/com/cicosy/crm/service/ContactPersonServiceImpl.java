package com.cicosy.crm.service;

import com.cicosy.crm.entity.ContactPerson;
import com.cicosy.crm.repo.ContactPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactPersonServiceImpl extends ContactPersonService {

    @Autowired
    private ContactPersonRepository contactPersonRepository;


    @Override
    public ContactPerson save(ContactPerson contactPerson) {
        return contactPersonRepository.save(contactPerson);
    }

    @Override
    public Optional<ContactPerson> findById(Long id) {
        return contactPersonRepository.findById(id);
    }

    @Override
    public List<ContactPerson> findAll() {
        return contactPersonRepository.findAll();
    }

    @Override
    public Page<ContactPerson> findAllContacts(Pageable pageable) {
        return contactPersonRepository.findAll(pageable);
    }
}
