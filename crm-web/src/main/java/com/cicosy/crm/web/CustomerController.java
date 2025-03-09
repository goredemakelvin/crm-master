package com.cicosy.crm.web;

import com.cicosy.crm.service.CustomerService;
import com.cicosy.crm.data.CustomerData;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer")
    public String register(Model model) {
        CustomerData customerData = new CustomerData();
        model.addAttribute("customer", customerData);
        return "customer-form.html";
    }

    @PostMapping("/save-customer")
    public String saveCustomer(@Valid @ModelAttribute("customer") CustomerData customerData,
                               BindingResult result,Model model,
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", result.getAllErrors().toString());
            return "customer-form.html";
        }
        customerService.createCustomer(customerData);
        redirectAttributes.addFlashAttribute("successMessage", "Contact Details saved successfully!");
        return "redirect:/customer-list";
    }

    @GetMapping("/customer-list")
    public String customerList(@ModelAttribute("successMessage") String successMessage,Model model) {
        model.addAttribute("successMessage", successMessage);
        return "customer-list.html";
    }



}
