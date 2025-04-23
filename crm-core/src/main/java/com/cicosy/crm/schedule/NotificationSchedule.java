package com.cicosy.crm.schedule;

import com.cicosy.crm.data.EmailTemplate;
import com.cicosy.crm.entity.Lead;
import com.cicosy.crm.entity.LeadSegment;
import com.cicosy.crm.entity.NotificationLog;
import com.cicosy.crm.entity.PromotionMessage;
import com.cicosy.crm.repo.LeadRepository;
import com.cicosy.crm.service.NotificationLogService;
import com.cicosy.crm.service.NotificationService;
import com.cicosy.crm.service.PromotionMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class NotificationSchedule {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private PromotionMessageService promotionMessageService;
    @Autowired
    private NotificationLogService notificationLogService;

    // Runs 5 seconds after the previous task completes
    //@Scheduled(fixedDelay = 5000)
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
                    if (currentCount <= maxCount) {

                        List<PromotionMessage> messages = promotionMessageService
                                .findAllByLeadSegment(leadSegment);
                        messages.forEach(promotionMessage -> {
//                            int scheduleDay = promotionMessage.getScheduleDay();
//                            LocalDateTime sendDate = lead.getCreatedDate().plusDays(scheduleDay);

                            //if (sendDate.equals(LocalDate.now())) {
                                EmailTemplate emailTemplate = new EmailTemplate();
                                emailTemplate.setContent(promotionMessage.getText());
                            String email = lead.getCustomer().getEmailAddress().get(0).getEmail();
                            emailTemplate.setTo(email);
                                emailTemplate.setSubject(promotionMessage.getSubject());
                                try {
                                    notificationService.sendEmailNotification(emailTemplate);
                                }catch (Exception e) {
                                    log.error(e.getMessage());
                                }


                                NotificationLog notiicationLog = new NotificationLog();
                                notiicationLog.setLead(lead);
                                notiicationLog.setPromotionMessage(promotionMessage);
                                notificationLogService.save(notiicationLog);

                           // }

                        });


                    }

                }
            });
            page++;
        } while (!leadPage.isLast());
    }
}
