package com.cicosy.crm.repo;

import com.cicosy.crm.entity.EmailAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailAddressRepository extends JpaRepository<EmailAddress, Long> {

}
