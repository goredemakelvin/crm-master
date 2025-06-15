package com.cicosy.crm.web;

import com.cicosy.crm.data.CustomerInformationData;
import com.cicosy.crm.data.BusinessInformationData;
import com.cicosy.crm.entity.BusinessInformation;
import com.cicosy.crm.entity.Customer;
import com.cicosy.crm.service.BusinessInformationService;
import com.cicosy.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class CustomerProfileController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BusinessInformationService businessInformationService;

    @GetMapping("/customer/{id}")
    public String customerProfile(@PathVariable Long id, Model model) {
        Optional<Customer> optionalCustomer = customerService.findById(id);
        if (optionalCustomer.isPresent()) {
            model.addAttribute("customer", optionalCustomer.get());

            BusinessInformation businessInformation = optionalCustomer.get().getBusinessInformation();
            Optional<BusinessInformation> optionalBusinessInformation = Optional.empty();
                CustomerInformationData c=new CustomerInformationData();
                BusinessInformationData b=new BusinessInformationData();
                CustomerInformationData customerInformation = c.getCustomerInformation(optionalCustomer.get());
                BusinessInformationData buzzdata = b.getCustomerBusinessInformation(businessInformation);
                customerInformation.setBusinessInformation(buzzdata);
                model.addAttribute("customerInformation",customerInformation);
                model.addAttribute("businessInformation",buzzdata);

        }
        return "customer-profile.html";
    }
}
