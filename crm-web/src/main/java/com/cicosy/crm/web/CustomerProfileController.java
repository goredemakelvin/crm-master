package com.cicosy.crm.web;

import com.cicosy.crm.entity.Customer;
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

    @GetMapping("/customer/{id}")
    public String customerProfile(@PathVariable Long id, Model model) {
        Optional<Customer> optionalCustomer = customerService.findById(id);
        model.addAttribute("customer", optionalCustomer.get());
        BasicCustomerInformation basicCustomerInformation=new BasicCustomerInformation();
        basicCustomerInformation.buildBasicCustomerInfomation(optionalCustomer.get());
        model.addAttribute("customerInformation",basicCustomerInformation);
        return "customer-profile.html";
    }
}
