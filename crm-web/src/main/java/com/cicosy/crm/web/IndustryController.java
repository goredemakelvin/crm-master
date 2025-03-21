package com.cicosy.crm.web;

import com.cicosy.crm.data.IndustryData;
import com.cicosy.crm.entity.Industry;
import com.cicosy.crm.service.IndustryService;
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
public class IndustryController {
    @Autowired
    private IndustryService industryService;

    @GetMapping("/industry-form")
    public String industry(Model model) {
        IndustryData industryData = new IndustryData();
        model.addAttribute("industry", industryData);
        return "industry-form.html";
    }

    @PostMapping("/save-industry")
    public String saveIndustry(@Valid @ModelAttribute("industry") IndustryData industryData,
                               BindingResult result, Model model,
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", result.getAllErrors().toString());
            return "industry-form.html";
        }
        Industry industry = null;
        if(industryData.getId()!=null) {
            Optional<Industry> optionalIndustry = industryService.findById(industryData.getId());
            industry=optionalIndustry.get();
        }else{
            industry = new Industry();
        }
        industry.setName(industryData.getName());
        industryService.save(industry);
        redirectAttributes.addFlashAttribute("successMessage", "Industry saved successfully!");
        return "redirect:/industry-list";
    }

    @GetMapping("/industry-list")
    public String getPaginatedIndustries(Model model,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "5") int size,
                                        @RequestParam(defaultValue = "id") String sortBy

    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<Industry> industryPage = industryService.findAllIndustries(pageable);
        model.addAttribute("industryPage", industryPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", industryPage.getTotalPages());
        model.addAttribute("sortBy", sortBy);
        return "industry-list.html";
    }

    @GetMapping("/industry/{id}")
    public String customerProfile(@PathVariable Long id, Model model) {
        Optional<Industry> optionalIndustry = industryService.findById(id);
        model.addAttribute("industry", optionalIndustry.get());
        return "industry-form.html";
    }


}
