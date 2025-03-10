package com.cicosy.crm.web;

import com.cicosy.crm.data.BusinessInformationData;
import com.cicosy.crm.data.CustomerData;
import com.cicosy.crm.entity.BusinessInformation;
import com.cicosy.crm.service.BusinessInformationService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BusinessInformationController {

    @Autowired
    private BusinessInformationService businessInformationService;

    @GetMapping("/new-business-info")
    public String captureBusinessInformation(@ModelAttribute("customerId") Long customerId, Model model) {
        BusinessInformation businessInformation = new BusinessInformation();
        businessInformation.setCustomerId(customerId);
        model.addAttribute("businessInformation", businessInformation);
        return  "business-info-form.html";
    }

    @PostMapping("/save-business-info")
    public String saveBusinessInformation(@Valid @ModelAttribute BusinessInformation  businessInformation,
                               BindingResult result, Model model,
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", result.getAllErrors().toString());
            return  "business-info-form.html";
        }
        businessInformationService.saveBuinessInformation(businessInformation);
        redirectAttributes.addFlashAttribute("successMessage", "Business Information saved successfully!");
        return "redirect:/customer-list";
    }




}
