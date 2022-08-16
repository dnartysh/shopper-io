package ru.shopper.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.shopper.config.AppConstants;
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
        userService.addBasicAttributes(model);

        return "pre/welcome";
    }

    @GetMapping
    public String redirectToMainPage(Model model) {
        userService.addBasicAttributes(model);

        return "pre/welcome";
    }

    @GetMapping("/welcome")
    public String welcomePage(Model model) {
        userService.addBasicAttributes(model);
        model.addAttribute("location", userService.getLastCurrentUserLocation());

        return "pre/welcome";
    }

    @PostMapping("/welcome")
    public String addUserPassword(Model model,
                                  @RequestParam String password) {
        userService.addBasicAttributes(model);
        userService.updateCurrentUser(password);

        return "pre/welcome";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "pre/login";
    }

    @PostMapping("/login")
    public String loginSuccess(Model model) {
        userService.addBasicAttributes(model);

        return "pre/welcome";
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "pre/registration";
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
            model.addAttribute("success", "Пользователь успешно зарегистрирован! " +
                    "Используйте форму входа для авторизации");
        } else {
            model.addAttribute("error", "Пользователь с логином '"
                    + username + "' уже существует!");
        }

        return "pre/registration";
    }

    @GetMapping("/settings")
    public String settingsPage(Model model) {
        userService.addBasicAttributes(model);

        return "settings";
    }

    @PostMapping("/settings")
    public String updateUser(Model model,
                             @RequestParam(required = false) MultipartFile file,
                             @RequestParam(required = false) boolean toPhotoDelete,
                             @RequestParam(required = false) String firstname,
                             @RequestParam(required = false) String lastname,
                             @RequestParam(required = false) String password,
                             @RequestParam(required = false)
                                 @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthdate) throws IOException {
        userService.addBasicAttributes(model);

        if (file != null) {
            userService.uploadUserPhoto(file);
        }

        if (toPhotoDelete) {
            userService.removeUserPhoto();
        }

        if (firstname != null | lastname != null | birthdate != null | password != null) {
            userService.updateCurrentUser(firstname, lastname, birthdate, password);
        }

        model.addAttribute("success", AppConstants.UPDATE_SUCCESS);

        return "settings";
    }
}