package com.cicosy.crm.web;

import com.cicosy.crm.entity.AppUser;
import com.cicosy.crm.repo.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackages = {"com.cicosy.crm.service","com.cicosy.crm.web","com.cicosy.crm.config","com.cicosy.crm.schedule"})
@EnableJpaRepositories("com.cicosy.crm.repo")
@EntityScan("com.cicosy.crm.entity")
@EnableScheduling
@EnableJpaAuditing(auditorAwareRef = "auditorProvider-v2")
public class CrmWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmWebApplication.class, args);
    }


    @Bean
    CommandLineRunner initUsers(AppUserRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.count() == 0) {
                AppUser user = new AppUser();
                user.setUsername("john");
                user.setPassword(encoder.encode("password"));
                user.setRoles(Arrays.stream(new String []{"USER"}).toList());
                repo.save(user);
            }
        };
    }


}
