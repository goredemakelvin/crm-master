package com.cicosy.crm.web;

import com.cicosy.crm.entity.NotificationLog;
import com.cicosy.crm.entity.Opportunity;
import com.cicosy.crm.service.NotificationLogService;
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
public class NotificationLogController {

    @Autowired
    private NotificationLogService notificationLogService;


    @GetMapping("/notification-log-list")
    public String getPaginatedOpportunities(Model model,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "50") int size,
                                            @RequestParam(defaultValue = "id") String sortBy

    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<NotificationLog> notificationLogPage = notificationLogService.findAllNotifications(pageable);
        model.addAttribute("notificationLogPage", notificationLogPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", notificationLogPage.getTotalPages());
        model.addAttribute("sortBy", sortBy);
        return "notification-log-list.html";
    }


}
