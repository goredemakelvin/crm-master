package com.cicosy.crm.service;

import com.cicosy.crm.data.BusinessFormData;
import com.cicosy.crm.entity.*;
import com.cicosy.crm.repo.BusinessInformationRepository;
import com.cicosy.crm.repo.CityRepository;
import com.cicosy.crm.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessInformationServiceImpl extends BusinessInformationService {

    @Autowired
    private BusinessInformationRepository businessInformationRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private  CityService cityService;
    @Autowired
    private IndustryService industryService;
    @Autowired
    private CountryService countryService;


    @Override
    public BusinessInformation save(BusinessInformation businessInformation) {
        return businessInformationRepository.save(businessInformation);
    }

    @Override
    public Optional<BusinessInformation> findById(Long id) {
        return businessInformationRepository.findById(id);
    }

    @Override
    public List<BusinessInformation> findAll() {
        return businessInformationRepository.findAll();
    }


    @Override
    public void saveBusinessInformation(BusinessFormData businessFormData) {
        BusinessInformation businessInformation = new BusinessInformation();
        businessInformation.setCompanyName(businessFormData.getCompanyName());
        businessInformation.setCompanySize(businessFormData.getCompanySize());
        if(businessFormData.getCustomerId()!=null) {
            Optional<Customer> optionalCustomer = customerService.findById(businessFormData.getCustomerId());
            if(optionalCustomer.isPresent()) {
                Customer customer = optionalCustomer.get();
                businessInformation.setCustomer(customer);
            }
            }
        if(businessFormData.getCity()!=null) {
            Optional<City> optionalCity = cityService.findById(businessFormData.getCity());
            if(optionalCity.isPresent()) {
                City city = optionalCity.get();
                businessInformation.setCity(city);
            }

        }
        if(businessFormData.getIndustry()!=null) {
            Optional<Industry> optionalIndustry = industryService.findById(businessFormData.getIndustry());
            if(optionalIndustry.isPresent()) {
                Industry industry = optionalIndustry.get();
                businessInformation.setIndustry(industry);
            }

        }

        if(businessFormData.getCountry()!=null) {
            Optional<Country> optionalCountry = countryService.findById(businessFormData.getCountry());
            if(optionalCountry.isPresent()) {
                Country country = optionalCountry.get();
                businessInformation.setCountry(country);
            }
        }
        businessInformationRepository.save(businessInformation);

    }

    @Override
    public Optional<BusinessInformation> findByCustomer(Customer customer) {
      return businessInformationRepository.findByCustomer(customer);
    }


}
