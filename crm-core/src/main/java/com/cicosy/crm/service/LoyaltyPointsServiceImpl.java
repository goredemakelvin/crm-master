package com.cicosy.crm.service;

import com.cicosy.crm.entity.LoyaltyPoints;
import com.cicosy.crm.repo.LoyaltyPointsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoyaltyPointsServiceImpl extends LoyaltyPointsService {

    @Autowired
    private LoyaltyPointsRepository loyaltyPointsRepository;

    @Override
    public LoyaltyPoints save(LoyaltyPoints loyaltyPoints) {
        return loyaltyPointsRepository.save(loyaltyPoints);
    }

    @Override
    public Optional<LoyaltyPoints> findById(Long id) {
        return loyaltyPointsRepository.findById(id);
    }

    @Override
    public List<LoyaltyPoints> findAll() {
        return loyaltyPointsRepository.findAll();
    }
}
