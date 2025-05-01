package com.cicosy.crm.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BusinessManagementController {
    @GetMapping("/business-management")
    public String leadManagement(Model model) {
        return "business-management.html";
    }

}
