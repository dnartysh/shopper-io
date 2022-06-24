package ru.dnartysh.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dnartysh.model.User;
import ru.dnartysh.service.UserService;


@Controller
@RequestMapping
public class AppController {
    UserService userService;

    public AppController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String redirectToLoginPage() {
        User user = userService.findUserByUsername
                (SecurityContextHolder.getContext().getAuthentication().getName());

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user.getPosition().getName();
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping(value = "/login")
    public String loginSuccess(Model model) {
        User user = userService.findUserByUsername
                (SecurityContextHolder.getContext().getAuthentication().getName());

        model.addAttribute("id", user.getId());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("firstname", user.getFirstname());
        model.addAttribute("lastname", user.getLastname());
        model.addAttribute("position", user.getPosition());
        model.addAttribute("userURI", user.getPosition());
        model.addAttribute("role", user.getRoles());
        model.addAttribute("registrationDate", user.getRegistrationDate());
        model.addAttribute("birthdate", user.getBirthdate());

        System.out.println(user.getPosition().getName());

        return user.getPosition().getName();
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@RequestParam String username,
                               @RequestParam String firstname,
                               @RequestParam String lastname,
                               @RequestParam String password) throws Exception {
        userService.saveUser(username, firstname, lastname, password);

        return "registration";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/user")
    public String userPage() {
        return "user";
    }
}