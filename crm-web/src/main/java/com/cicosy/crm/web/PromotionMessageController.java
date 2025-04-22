package com.cicosy.crm.web;

import com.cicosy.crm.entity.PromotionMessage;
import com.cicosy.crm.service.LeadSegmentService;
import com.cicosy.crm.service.PromotionMessageService;
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
public class PromotionMessageController {
    @Autowired
    private PromotionMessageService promotionMessageService;
    @Autowired
    private LeadSegmentService leadSegmentService;

    @GetMapping("/promotion-message-form")
    public String industry(Model model) {
        model.addAttribute("promotionMessage", new PromotionMessage());
        model.addAttribute("leadSegments", leadSegmentService.findAll());
        return "promotion-message-form.html";
    }

    @PostMapping("/save-promotion-message")
    public String saveLead(@Valid PromotionMessage promotionMessage,
                           BindingResult result, Model model,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", result.getAllErrors().toString());
            return "promotion-message-form.html";
        }
        PromotionMessage message = null;
        if (promotionMessage.getId() != null) {
            Optional<PromotionMessage> optionalSegment = promotionMessageService.findById(promotionMessage.getId());
            message = optionalSegment.get();
        } else {
            message = new PromotionMessage();
        }
        message.setText(promotionMessage.getText());
        message.setLeadSegment(promotionMessage.getLeadSegment());
        promotionMessageService.save(message);
        redirectAttributes.addFlashAttribute("successMessage", "Lead Segment saved successfully!");
        return "redirect:/promotion-message-list";
    }

    @GetMapping("/promotion-message-list")
    public String getPaginatedIndustries(Model model,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "5") int size,
                                         @RequestParam(defaultValue = "id") String sortBy

    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<PromotionMessage> promotionMessagePage = promotionMessageService.findAllPromotionMessages(pageable);
        model.addAttribute("promotionMessagePage", promotionMessagePage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", promotionMessagePage.getTotalPages());
        model.addAttribute("sortBy", sortBy);
        return "promotion-message-list.html";
    }

    @GetMapping("/promotion-message/{id}")
    public String customerProfile(@PathVariable Long id, Model model) {
        Optional<PromotionMessage> optionalPromotionMessage = promotionMessageService.findById(id);
        model.addAttribute("promotionMessage", optionalPromotionMessage.get());
        model.addAttribute("leadSegments", leadSegmentService.findAll());
        return "promotion-message-form.html";
    }


}
