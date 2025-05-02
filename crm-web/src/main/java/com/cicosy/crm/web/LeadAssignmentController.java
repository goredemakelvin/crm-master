package com.cicosy.crm.web;

import com.cicosy.crm.data.ContactPersonForm;
import com.cicosy.crm.data.LeadConversionForm;
import com.cicosy.crm.entity.ContactPerson;
import com.cicosy.crm.entity.Customer;
import com.cicosy.crm.entity.Lead;
import com.cicosy.crm.entity.LeadStage;
import com.cicosy.crm.service.ContactPersonService;
import com.cicosy.crm.service.CustomerService;
import com.cicosy.crm.service.LeadConversionService;
import com.cicosy.crm.service.LeadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    @Autowired
    private LeadConversionService leadConversionService;

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
        return "redirect:/lead-list";

    }

    @RequestMapping(
            path = "/assign-contact-person",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String assignContactPersons(ContactPersonForm form, Model redirectAttributes) {
        Optional<Lead> optionalLead = leadService.findById(form.getLeadContactId());
        if (optionalLead.isPresent()) {
            Lead leadSaved = optionalLead.get();
            Optional<ContactPerson> contactPerson = contactPersonService.findById(form.getLeadContactPerson());
            if (contactPerson.isPresent()) {
                leadSaved.setContactPerson(contactPerson.get());
                leadService.save(leadSaved);
            }
        }
        redirectAttributes.addAttribute("successMessage", "Lead assignment successful!");
        return "redirect:/lead-list";

    }

    @RequestMapping(
            path = "/convert-lead",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String convertLead(LeadConversionForm form, Model model, RedirectAttributes redirectAttributes) {
        Optional<Lead> optionalLead = leadService.findById(form.getId());
        if (optionalLead.isPresent()) {
            Lead leadSaved = optionalLead.get();
            leadSaved.setLeadStage(LeadStage.valueOf(form.getLeadStage()));
            leadSaved.setConverted(true);
            leadService.save(leadSaved);
            leadConversionService.convertLead(leadSaved);
        }
        redirectAttributes.addFlashAttribute("successMessage", "Lead conversion successful!");
        return "redirect:/lead-list";

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