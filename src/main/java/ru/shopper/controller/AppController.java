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
        userService.addBasicAttributes(model);

        return "account/" + user.getPosition().getName();
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping(value = "/login")
    public String loginSuccess(Model model) {
        User currentUser = userService.getCurrentUser();
        userService.addBasicAttributes(model);

        return "account/" + currentUser.getPosition().getName();
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(Model model,
                               @RequestParam String username,
                               @RequestParam String firstname,
                               @RequestParam String lastname,
                               @RequestParam String password) throws Exception {
        User user = userService.getUserByUsername(username);

        if (user == null) {
            userService.createUser(username, firstname, lastname, password);
        } else {
            model.addAttribute("error", "???????????????????????? ?? ?????????????? - "
                    + username + " ?????? ????????????????????!");
        }

        return "registration";
    }

    @GetMapping("/settings")
    public String settingsPage(Model model) {
        userService.addBasicAttributes(model);

        return "settings";
    }

    @PostMapping("/settings")
    public String updateUser(@RequestParam(required = false) MultipartFile file,
                              @RequestParam(required = false) boolean toDelete,
                              @RequestParam(required = false) String firstname,
                              @RequestParam(required = false) String lastname,
                              @RequestParam(required = false) String password,
                              @RequestParam(required = false)
                                  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
                              Model model) throws IOException {
        userService.addBasicAttributes(model);

        if (file != null) {
            userService.uploadUserPhoto(file);
        }

        if (toDelete) {
            userService.removeUserPhoto();
        }

        if (firstname != null | lastname != null | birthdate != null) {
            if (password != null) {
                userService.updateUser(firstname, lastname, birthdate, password);
            } else {
                userService.updateUser(firstname, lastname, birthdate);
            }
        }

        return "settings";
    }
}