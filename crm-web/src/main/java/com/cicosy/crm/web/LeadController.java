package com.cicosy.crm.web;

import com.cicosy.crm.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LeadController {
    @Autowired
    private LeadService leadService;

    @GetMapping("/lead-list")
    public String list(Model mdodel,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "5") int size,
                       @RequestParam(defaultValue = "id") String sortBy, @ModelAttribute("successMessage") String successMessage
                       ){
        mdodel.addAttribute("leads", leadService.findAll());
        return "lead-list";
    }

}
