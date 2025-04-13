package com.cicosy.crm.web;

import com.cicosy.crm.entity.Account;
import com.cicosy.crm.entity.Contact;
import com.cicosy.crm.service.AccountService;
import com.cicosy.crm.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;


    @GetMapping("/contact-lead-list")
    public String getPaginatedCities(Model model,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size,
                                     @RequestParam(defaultValue = "id") String sortBy

    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<Contact> contactPage = contactService.findAllContacts(pageable);
        model.addAttribute("contactPage", contactPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", contactPage.getTotalPages());
        model.addAttribute("sortBy", sortBy);
        return "contact-list.html";
    }


}
