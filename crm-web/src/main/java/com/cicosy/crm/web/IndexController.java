package com.cicosy.crm.web;

import com.cicosy.crm.data.LeadData;
import com.cicosy.crm.service.LeadService;
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
public class IndexController {

    @Autowired
    private LeadService leadService;

    @GetMapping("/")
    public String index() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy, Model model) {


        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<LeadData> leadsPage = leadService.findAllLeads(pageable).map(item -> {
            LeadData lead = new LeadData();
            return lead.getLeadData(item);
        });
        model.addAttribute("leadsPage", leadsPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", leadsPage.getTotalPages());
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("leadsCount",leadService.count());


        return "dashboard.html";
    }

}
