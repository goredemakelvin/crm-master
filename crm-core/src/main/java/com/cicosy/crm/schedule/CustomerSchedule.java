package com.cicosy.crm.schedule;

import com.cicosy.crm.data.EmailTemplate;
import com.cicosy.crm.entity.Customer;
import com.cicosy.crm.entity.LeadSegment;
import com.cicosy.crm.entity.NotificationLog;
import com.cicosy.crm.entity.PromotionMessage;
import com.cicosy.crm.repo.CustomerRepository;
import com.cicosy.crm.service.NotificationLogService;
import com.cicosy.crm.service.NotificationService;
import com.cicosy.crm.service.PromotionMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class CustomerSchedule {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PromotionMessageService promotionMessageService;
    @Autowired
    private NotificationLogService notificationLogService;

    // Runs 5 seconds after the previous task completes
    @Scheduled(fixedDelay = 5000)
    public void processCustomerMessages() {
        log.info("-------fixedDelayTask  sending notifications-----");
        int page = 0;
        int size = 50; // adjust based on performance
        Page<Customer> customerPage;

        do {

            customerPage = customerRepository.findAll(PageRequest.of(page, size));
            customerPage.getContent().forEach(customer -> {
                // process each user
                if (customer.getLeadSegment() != null) {
                    LeadSegment leadSegment = customer.getLeadSegment();
                    int maxCount = leadSegment.getMaxNotificationCount();
                    int currentCount = leadSegment.getCurrentNotificationCount();
                    if (currentCount <= maxCount) {
                        List<PromotionMessage> messages = promotionMessageService
                                .findAllByLeadSegment(leadSegment);
                        messages.forEach(promotionMessage -> {
                            int scheduleDay = promotionMessage.getScheduleDay();
                            LocalDateTime sendDate = customer.getCreatedDate().plusDays(scheduleDay);
                            EmailTemplate emailTemplate = new EmailTemplate();
                            emailTemplate.setContent(promotionMessage.getText());
                            if (customer.getEmailAddress() == null || customer.getEmailAddress().isEmpty()) {
                                log.warn("No email address found for customer: {}", customer.getId());
                                return;
                            } else {
                                String email = customer.getEmailAddress().get(0).getEmail();
                                emailTemplate.setTo(email);
                                emailTemplate.setSubject(promotionMessage.getSubject());
                                try {
                                    notificationService.sendEmailNotification(emailTemplate);
                                } catch (Exception e) {
                                    log.error(e.getMessage());
                                }
                                NotificationLog notiicationLog = new NotificationLog();
                                notiicationLog.setCustomer(customer);
                                notiicationLog.setPromotionMessage(promotionMessage);
                                notificationLogService.save(notiicationLog);
                            }
                        });

                    }


                }
            });
            page++;
        } while (!customerPage.isLast());
    }
}
