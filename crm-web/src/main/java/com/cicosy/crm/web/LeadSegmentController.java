package com.cicosy.crm.web;

import com.cicosy.crm.entity.LeadSegment;
import com.cicosy.crm.service.LeadSegmentService;
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
public class LeadSegmentController {
    @Autowired
    private LeadSegmentService leadSegmentService;

    @GetMapping("/lead-segment-form")
    public String industry(Model model) {
        model.addAttribute("leadSegment", new LeadSegment());
        return "lead-segment-form.html";
    }

    @PostMapping("/save-lead-segment")
    public String saveLead(@Valid LeadSegment leadSegment,
                           BindingResult result, Model model,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", result.getAllErrors().toString());
            return "lead-segment-form.html";
        }
        LeadSegment segment = null;
        if (leadSegment.getId() != null) {
            Optional<LeadSegment> optionalSegment = leadSegmentService.findById(leadSegment.getId());
            segment = optionalSegment.get();
        } else {
            segment = new LeadSegment();
        }
        segment.setName(leadSegment.getName());
        leadSegmentService.save(segment);
        redirectAttributes.addFlashAttribute("successMessage", "Lead Segment saved successfully!");
        return "redirect:/lead-segment-list";
    }

    @GetMapping("/lead-segment-list")
    public String getPaginatedIndustries(Model model,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "5") int size,
                                         @RequestParam(defaultValue = "id") String sortBy

    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<LeadSegment> leadSegmentPage = leadSegmentService.findAllLeadSegment(pageable);
        model.addAttribute("leadSegmentPage", leadSegmentPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", leadSegmentPage.getTotalPages());
        model.addAttribute("sortBy", sortBy);
        return "lead-segment-list.html";
    }

    @GetMapping("/lead-segment/{id}")
    public String customerProfile(@PathVariable Long id, Model model) {
        Optional<LeadSegment> optionalLeadSegment = leadSegmentService.findById(id);
        model.addAttribute("leadSegment", optionalLeadSegment.get());
        return "lead-segment-form.html";
    }


}
