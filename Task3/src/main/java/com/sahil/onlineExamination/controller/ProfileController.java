package com.sahil.onlineExamination.controller;

import com.sahil.onlineExamination.model.User;
import com.sahil.onlineExamination.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        User user = userService.findByUsername(currentUser.getUsername());
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@AuthenticationPrincipal UserDetails currentUser,
                                @RequestParam String name, @RequestParam String email, Model model) {
        User user = userService.findByUsername(currentUser.getUsername());
        user.setName(name);
        user.setEmail(email);
        userService.save(user);
        return "redirect:/profile";
    }

    @PostMapping("/profile/password")
    public String updatePassword(@AuthenticationPrincipal UserDetails currentUser,
                                 @RequestParam String newPassword) {
        User user = userService.findByUsername(currentUser.getUsername());
        user.setPassword(newPassword);
        userService.save(user);
        return "redirect:/profile";
    }
}
