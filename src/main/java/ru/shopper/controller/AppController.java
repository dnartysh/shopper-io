package ru.shopper.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.shopper.model.User;
import ru.shopper.service.UserService;

import java.io.IOException;
import java.time.LocalDate;


@Controller
@RequestMapping
public class AppController {
    UserService userService;

    public AppController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String redirectToPage(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("currentUser", userService.getSimpleFieldsForCurrentUser());

        return "account/" + user.getPosition().getName();
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping(value = "/login")
    public String loginSuccess(Model model) {
        User currentUser = userService.getCurrentUser();
        model.addAttribute("currentUser", userService.getSimpleFieldsForCurrentUser());

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
    public String settingsPage(Model model) {
        model.addAttribute("currentUser", userService.getSimpleFieldsForCurrentUser());

        return "settings";
    }

    @PostMapping("/settings")
    public String uploadPhoto(@RequestParam(required = false) MultipartFile file,
                              @RequestParam(required = false) boolean toDelete,
                              @RequestParam(required = false) String firstname,
                              @RequestParam(required = false) String lastname,
                              @RequestParam(required = false)
                                  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
                              Model model) throws IOException {
        model.addAttribute("currentUser", userService.getSimpleFieldsForCurrentUser());

        if (file != null) {
            userService.uploadUserPhoto(file);
        }

        if (toDelete) {
            userService.removeUserPhoto();
        }

        if (firstname != null | lastname != null | birthdate != null) {
            userService.updateUser(firstname, lastname, birthdate);
        }

        return "settings";
    }
}