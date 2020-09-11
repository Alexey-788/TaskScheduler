package com.alex.controller;

import com.alex.domain.Role;
import com.alex.domain.Status;
import com.alex.domain.UserEntity;
import com.alex.dto.UserDto;
import com.alex.repository.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class RegistrationController {

    UserRepo userRepo;

    public RegistrationController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/registration")
    public String registration() {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String registration(UserDto userDto, Model model) {
        if (userRepo.findByLogin(userDto.getLogin()).orElse(null) != null) {
            model.addAttribute("error", "User with this login exist");
            return "auth/registration";
        }
        userRepo.save(new UserEntity(userDto.getName(), userDto.getLogin(),
                new BCryptPasswordEncoder(12).encode(userDto.getPassword()),
                Role.USER, Status.ACTIVE));
        return "redirect:/auth/login";
    }
}
