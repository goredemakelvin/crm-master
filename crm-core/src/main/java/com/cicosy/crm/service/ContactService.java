package com.cicosy.crm.service;

import com.cicosy.crm.entity.Account;
import com.cicosy.crm.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class ContactService extends AbstractService<Contact> {
    public abstract Page<Contact> findAllContacts(Pageable pageable);
}
