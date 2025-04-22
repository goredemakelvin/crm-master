package com.cicosy.crm.web;

import com.cicosy.crm.entity.Customer;
import com.cicosy.crm.entity.Lead;
import com.cicosy.crm.service.ContactPersonService;
import com.cicosy.crm.service.CustomerService;
import com.cicosy.crm.service.LeadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class LeadAssignmentController {

    @Autowired
    private LeadService leadService;
    @Autowired
    private ContactPersonService contactPersonService;
    @Autowired
    private CustomerService customerService;

    @GetMapping("/assign-lead/{id}")
    public String leadForm(@PathVariable Long id, Model model) {
        Optional<Lead> optionalLead = leadService.findById(id);
        if (optionalLead.isPresent()) {
            model.addAttribute("contactPersons", contactPersonService.findAll());
            model.addAttribute("lead", optionalLead.get());
        }
        return "lead-assignment-form.html";
    }

    @PostMapping("/save-lead")
    public String saveLead(@Valid Lead lead,
                           BindingResult result, Model model,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", result.getAllErrors().toString());
            return "lead-assignment-form.html";
        }
        Optional<Lead> optionalLead = leadService.findById(lead.getId());
        if (optionalLead.isPresent()) {
            Lead leadSaved = optionalLead.get();
            leadSaved.setContactPerson(lead.getContactPerson());
            leadService.save(leadSaved);
        }
        redirectAttributes.addFlashAttribute("successMessage", "Lead saved successfully!");
        return "redirect:/dashboard";
    }

    @GetMapping("/convert-lead/{id}")
    public String convertLead(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Lead> optionalLead = leadService.findById(id);
        if (optionalLead.isPresent()) {
            Lead leadSaved = optionalLead.get();
            Customer customer = leadSaved.getCustomer();
            customer.setConverted(true);
            customerService.save(customer);
            leadSaved.setConverted(true);
            leadService.save(leadSaved);
        }
        redirectAttributes.addFlashAttribute("successMessage", "Lead conversion successful!");
        return "redirect:/dashboard";

    }



    @GetMapping("/lead-profile/{id}")
    public String leadPage(@PathVariable Long id, Model model) {
        Optional<Lead> optionalLead = leadService.findById(id);
        if (optionalLead.isPresent()) {
            model.addAttribute("lead", optionalLead.get());
        }
        return "lead-profile.html";
    }


}