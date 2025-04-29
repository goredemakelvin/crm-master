package com.cicosy.crm.web;

import com.cicosy.crm.entity.ContactPerson;
import com.cicosy.crm.service.ContactPersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class ContactPersonController {
    @Autowired
    private ContactPersonService contactPersonService;

    @GetMapping("/contact-form")
    public String city(Model model) {
        ContactPerson contactPerson = new ContactPerson();
        model.addAttribute("contact", contactPerson);
        return "contact-form.html";
    }

    @PostMapping("/save-contact")
    public String saveCity(@Valid ContactPerson contactPerson,
                           BindingResult result, Model model,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", result.getAllErrors().toString());
            return "contact-form.html";
        }

        contactPersonService.save(contactPerson);
        // redirectAttributes.addFlashAttribute("successMessage", "City saved successfully!");
        //redirectAttributes.addAttribute("cityId", city.getId());
        return "redirect:/contact-list";
    }

    @GetMapping("/contact-list")
    public String getPaginatedCities(Model model,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size,
                                     @RequestParam(defaultValue = "id") String sortBy

    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<ContactPerson> cityPage = contactPersonService.findAllContacts(pageable);
        model.addAttribute("contactPage", cityPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", cityPage.getTotalPages());
        model.addAttribute("sortBy", sortBy);
        return "contact-person-list.html";
    }

    @GetMapping("/contact/{id}")
    public String getCity(@PathVariable Long id, Model model) {
        Optional<ContactPerson> optionalContactPerson = contactPersonService.findById(id);
        if (optionalContactPerson.isPresent()) {
            model.addAttribute("contact", optionalContactPerson.get());
        }
        return "contact-form.html";
    }


}
