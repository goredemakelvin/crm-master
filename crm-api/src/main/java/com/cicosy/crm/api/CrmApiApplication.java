package com.cicosy.crm.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages = {"com.cicosy.crm.service","com.cicosy.crm.api"})
@EnableJpaRepositories("com.cicosy.crm.repo")
@EntityScan("com.cicosy.crm.entity")
public class CrmApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmApiApplication.class, args);
    }

}
