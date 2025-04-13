package com.cicosy.crm.web;

import com.cicosy.crm.entity.Opportunity;
import com.cicosy.crm.service.OpportunityService;
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
public class OpportunityController {

    @Autowired
    private OpportunityService opportunityService;


    @GetMapping("/opportunity-list")
    public String getPaginatedOpportunities(Model model,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "5") int size,
                                            @RequestParam(defaultValue = "id") String sortBy

    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<Opportunity> opportunityPage = opportunityService.findAllOpportunities(pageable);
        model.addAttribute("opportunityPage", opportunityPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", opportunityPage.getTotalPages());
        model.addAttribute("sortBy", sortBy);
        return "opportunity-list.html";
    }


}
