package com.cicosy.crm.service;

import com.cicosy.crm.entity.Account;
import com.cicosy.crm.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class AccountService extends AbstractService<Account> {
    public abstract Page<Account> findAllAccounts(Pageable pageable);
}
