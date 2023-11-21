package com.carpatotrip.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.carpatotrip.web.dto.RegistrationDto;
import com.carpatotrip.web.model.UserEntity;
import com.carpatotrip.web.service.UserEntityService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AuthController {

    private final UserEntityService userService;
    
    @GetMapping("/")
    public String homePage() {
        return "index";
    }
    
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(
            @Valid @ModelAttribute("user") RegistrationDto user, 
            BindingResult result, Model model) {

        UserEntity existingUser = userService.findByEmail(user.getEmail());
        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            return "redirect/register?fail";
        }
        existingUser = userService.findByLastName(user.getLastName());
        if (existingUser != null && existingUser.getLastName() != null && !existingUser.getLastName().isEmpty()) {
            return "redirect/register?fail";
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/clubs?success";
    }

}
