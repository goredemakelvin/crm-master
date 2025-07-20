package com.cicosy.crm.web;

import com.cicosy.crm.data.LeadData;
import com.cicosy.crm.repo.*;
import com.cicosy.crm.service.CustomerService;
import com.cicosy.crm.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasRole('ADMIN')")
public class IndexController {

    @Autowired
    private LeadService leadService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private BusinessInformationRepository businessInformationRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private OpportunityRepository opportunityRepository;
    @Autowired
    private NotificationLogRepository notificationLogRepository;

    @GetMapping("/")
    public String index() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy, Model model) {


        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<LeadData> leadsPage = leadService.findAllLeads(pageable).map(item -> {
            LeadData lead = new LeadData();
            return lead.getLeadData(item);
        });
        model.addAttribute("customerCount",customerRepository.count());
        model.addAttribute("cityCount",cityRepository.count());
        model.addAttribute("businessInfoCount",businessInformationRepository.count());
        model.addAttribute("countryCount",countryRepository.count());
        model.addAttribute("opportunityCount",opportunityRepository.count());
        model.addAttribute("contactCount",contactRepository.count());
        model.addAttribute("accountCount",accountRepository.count());
        model.addAttribute("notificationCount",accountRepository.count());
        model.addAttribute("leadsPage", leadsPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", leadsPage.getTotalPages());
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("leadsCount",leadService.findAllLeads(pageable).getTotalElements());
        model.addAttribute("customersCount",customerService.countByConverted(true));
        return "dashboard.html";
    }

}
