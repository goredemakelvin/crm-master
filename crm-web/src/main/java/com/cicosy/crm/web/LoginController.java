package com.cicosy.crm.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @PostMapping("/logout")
   public String logout() {
        return "logout.html";
    }
}
