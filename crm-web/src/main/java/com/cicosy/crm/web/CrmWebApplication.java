package com.cicosy.crm.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"com.cicosy.crm.service","com.cicosy.crm.web","com.cicosy.crm.config","com.cicosy.crm.schedule"})
@EnableJpaRepositories("com.cicosy.crm.repo")
@EntityScan("com.cicosy.crm.entity")
@EnableScheduling
public class CrmWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmWebApplication.class, args);
    }

}
