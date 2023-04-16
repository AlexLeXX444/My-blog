package com.app.blog.controllers;

import com.app.blog.models.User;
import com.app.blog.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user/login")
    public String userLoginPage() {
        return "users/user_login";
    }

    @GetMapping("/user/registration")
    public String userRegistrationPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "users/user_registration";
    }

    @PostMapping("/user/registration")
    public String userRegistration(@ModelAttribute("user") User user) {
        return switch (userService.registerNew(user)) {
            case (0) -> "redirect:/user/success_register";
            case (1) -> "redirect:/user/error_login";
            case (2) -> "redirect:/user/error_password";
            case (3) -> "redirect:/user/error_email";
            default -> "redirect:/";
        };
    }

    @GetMapping("/user/registration/success_register")
    public String userRegistrationSuccessPage() {
        return "users/success";
    }
}
