package com.cicosy.crm.web;

import com.cicosy.crm.data.RegistrationRequest;
import com.cicosy.crm.entity.AppUser;
import com.cicosy.crm.repo.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RegistrationController {

    @Autowired
    private AppUserRepository userRepo;
    @Autowired private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showForm(Model model) {
        model.addAttribute("registrationRequest", new RegistrationRequest());
        model.addAttribute("allRoles", List.of("USER", "ADMIN")); // roles to choose from
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegistrationRequest request) {
        AppUser user = new AppUser();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(request.getSelectedRoles());
        userRepo.save(user);
        return "redirect:/login";
    }
}
