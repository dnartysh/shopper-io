package ru.dnartysh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dnartysh.model.User;
import ru.dnartysh.service.UserService;

import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping
public class AppController {
    UserService userService;

    public AppController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String redirectToPage(Model model, HttpServletResponse response) {
        User user = userService.getCurrentUser();
        model.addAttribute("currentUser", userService.getSimpleFieldsForCurrentUser());

        userService.setCookie(response);

        return "account/" + user.getPosition().getName();
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping(value = "/login")
    public String loginSuccess(Model model, HttpServletResponse response) {
        User currentUser = userService.getCurrentUser();
        model.addAttribute("currentUser", userService.getSimpleFieldsForCurrentUser());

        userService.setCookie(response);

        return "account/" + currentUser.getPosition().getName();
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

    @GetMapping("/settings")
    public String settingsPage(Model model, HttpServletResponse response) {
        model.addAttribute("currentUser", userService.getSimpleFieldsForCurrentUser());

        return "settings";
    }
}