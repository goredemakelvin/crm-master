package com.cicosy.crm.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerManagementController {
    @GetMapping("/customer-management")
    public String customerManagement(Model model) {
        return "customer-management.html";
    }

}
