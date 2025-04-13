package com.cicosy.crm.service;

import com.cicosy.crm.entity.Account;
import com.cicosy.crm.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl extends AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account save(Account Account) {
        return accountRepository.save(Account);
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Page<Account> findAllAccounts(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }
}
