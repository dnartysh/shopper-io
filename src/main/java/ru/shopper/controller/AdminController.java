package ru.shopper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.shopper.config.AppConstants;
import ru.shopper.model.User;
import ru.shopper.service.UserService;

import java.time.LocalDate;

@Controller
@RequestMapping("/admin")
public class AdminController {
    UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAdminPage(Model model) {
        userService.addBasicAttributes(model);

        return "account/admin";
    }

    @GetMapping("/user")
    public String getUsersPage(Model model) {
        userService.addBasicAttributes(model);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("positions", userService.getPositions());

        return "repo/user";
    }

    @PostMapping("/user")
    public String saveUser(Model model,
                           @RequestParam String username,
                           @RequestParam String firstname,
                           @RequestParam String lastname,
                           @RequestParam(required = false) String password,
                           @RequestParam(required = false) String position,
                           @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
                           @RequestParam(defaultValue = "false") boolean active,
                           @RequestParam(defaultValue = "false") boolean isNew) throws Exception {
        userService.addBasicAttributes(model);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("positions", userService.getPositions());

        User user = userService.getUserByUsername(username);

        if (isNew) {
            if (user != null) {
                model.addAttribute("error", "Пользователь с логином '"
                        + username + "' уже существует!");

                return "repo/user";
            }

            userService.createUser(username, firstname, lastname, password, position, birthdate, active);
            model.addAttribute("success", AppConstants.CREATE_SUCCESS);
        } else {
            if (user == null) {
                model.addAttribute("error", "Пользователь с логином '" + username + "' не найден!");

                return "repo/user";
            }

            userService.updateUser(username, firstname, lastname, position, birthdate, password, active);
            model.addAttribute("success", AppConstants.UPDATE_SUCCESS);
        }

        return "repo/user";
    }

    @GetMapping("/statistic")
    public String getStatisticPage(Model model) {
        userService.addBasicAttributes(model);

        return "statistic";
    }

    @GetMapping("/shop")
    public String getShopPage(Model model) {
        userService.addBasicAttributes(model);

        return "repo/shop";
    }

    @GetMapping("/storage")
    public String getStoragePage(Model model) {
        userService.addBasicAttributes(model);

        return "repo/storage";
    }
}
