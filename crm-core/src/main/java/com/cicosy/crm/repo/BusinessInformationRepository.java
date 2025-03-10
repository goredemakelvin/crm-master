package com.cicosy.crm.repo;

import com.cicosy.crm.entity.BusinessInformation;
import com.cicosy.crm.entity.EmailAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessInformationRepository extends JpaRepository<BusinessInformation, Long> {

}
