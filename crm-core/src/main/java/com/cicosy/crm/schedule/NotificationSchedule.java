package com.cicosy.crm.schedule;

import com.cicosy.crm.data.NotificationMessage;
import com.cicosy.crm.entity.Lead;
import com.cicosy.crm.entity.LeadSegment;
import com.cicosy.crm.repo.LeadRepository;
import com.cicosy.crm.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationSchedule {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private LeadRepository leadRepository;

    // Runs 5 seconds after the previous task completes
    @Scheduled(fixedDelay = 5000)
    public void processAllLeads() {
        log.info("-------fixedDelayTask  sending notifications-----");
        int page = 0;
        int size = 50; // adjust based on performance
        Page<Lead> leadPage;

        do {

            leadPage = leadRepository.findAll(PageRequest.of(page, size));
            leadPage.getContent().forEach(lead -> {
                // process each user
                if (lead.getLeadSegment() != null) {

                    LeadSegment leadSegment = lead.getLeadSegment();
                    int maxCount = leadSegment.getMaxNotificationCount();
                    int currentCount = leadSegment.getCurrentNotificationCount();
                    if (currentCount < maxCount) {
                        NotificationMessage notificationMessage = new NotificationMessage();
                        notificationMessage.setMessage("Hey First Email");
                        notificationService.sendEmailNotification(lead, notificationMessage);
                    }

                }
            });
            page++;
        } while (!leadPage.isLast());
    }
}
