package com.cicosy.crm.service;

import com.cicosy.crm.entity.ContactPerson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class ContactPersonService extends AbstractService<ContactPerson> {
    public abstract Page<ContactPerson> findAllContacts(Pageable pageable);
}
