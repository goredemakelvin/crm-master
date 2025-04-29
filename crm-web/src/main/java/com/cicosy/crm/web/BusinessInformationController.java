package com.cicosy.crm.web;

import com.cicosy.crm.data.BusinessFormData;
import com.cicosy.crm.entity.BusinessInformation;
import com.cicosy.crm.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class BusinessInformationController {

    @Autowired
    private BusinessInformationService businessInformationService;
    @Autowired
    private IndustryService industryService;
    @Autowired
    private CityService cityService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CountryService countryService;

    @GetMapping("/new-business-info")
    public String captureBusinessInformation(@ModelAttribute("customerId") Long customerId, Model model) {
        BusinessFormData businessFormData = new BusinessFormData();
        businessFormData.setCustomerId(customerId);
        model.addAttribute("businessInformation", businessFormData);
        model.addAttribute("industries", industryService.findAll());
        model.addAttribute("cities", cityService.findAll());
        model.addAttribute("countries",countryService.findAll());
        return  "business-info-form.html";
    }

    @PostMapping("/save-business-info")
    public String saveBusinessInformation(@Valid @ModelAttribute BusinessFormData  businessInformation,
                               BindingResult result, Model model,
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", result.getAllErrors().toString());
            return  "business-info-form.html";
        }
        businessInformationService.saveBusinessInformation(businessInformation);
        redirectAttributes.addFlashAttribute("successMessage", "Business Information saved successfully!");
        return "redirect:/customer-list";
    }

    @GetMapping("/business-info-list")
    public String getPaginatedBusinessInformation(Model model,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "20") int size,
                                        @RequestParam(defaultValue = "id") String sortBy


    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<BusinessInformation> businessInformationPage = businessInformationService.findAllBusinessInformation(pageable);
        model.addAttribute("businessInformationPage", businessInformationPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", businessInformationPage.getTotalPages());
        model.addAttribute("sortBy", sortBy);
        return "business-info-list.html";
    }


    @GetMapping("/business-info/{id}")
    public String editBusinessInformation(@PathVariable Long id, Model model){
        Optional<BusinessInformation> businessInformation = businessInformationService.findById(id);
        if (businessInformation.isPresent()) {
            model.addAttribute("businessInformation", businessInformation.get());
            model.addAttribute("industries", industryService.findAll());
            model.addAttribute("cities", cityService.findAll());
            model.addAttribute("countries",countryService.findAll());

        }
        return  "business-info-form.html";

    }

}
