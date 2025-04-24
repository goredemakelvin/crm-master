package com.cicosy.crm.web;

import com.cicosy.crm.entity.AppUser;
import com.cicosy.crm.repo.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class UserAdminController {
    @Autowired
    private AppUserRepository userRepo;

    @GetMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute("users", userRepo.findAll());
        model.addAttribute("availableRoles", List.of("USER", "ADMIN"));
        return "admin-users";
    }

    @PostMapping("/users/{id}/update-roles")
    public String updateRoles(@PathVariable Long id, @RequestParam(required = false) List<String> roles
    , RedirectAttributes redirectAttributes) {
        AppUser user = userRepo.findById(id).orElseThrow();
        user.setRoles(roles != null ? roles : new ArrayList<>());
        userRepo.save(user);
        redirectAttributes.addFlashAttribute("errorMessage", "Roles updated for user: " + user.getUsername());
        return "redirect:/admin/users";
    }
}
