package com.cicosy.crm.web;

import com.cicosy.crm.data.CustomerData;
import com.cicosy.crm.entity.Customer;
import com.cicosy.crm.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
                               BindingResult result, Model model,
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
    public String getPaginatedCustomers(Model model,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "5") int size,
                                        @RequestParam(defaultValue = "id") String sortBy, @ModelAttribute("successMessage") String successMessage


    ) {
        if (!ObjectUtils.isEmpty(successMessage)) {
            model.addAttribute("successMessage", successMessage);
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<Customer> customerPage = customerService.findAllCustomers(pageable);
        model.addAttribute("customerPage", customerPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", customerPage.getTotalPages());
        model.addAttribute("sortBy", sortBy);
        return "customer-list.html";
    }


}
