package ru.shopper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shopper.service.UserService;

@Controller
@RequestMapping("/newcomer")
public class NewcomerController {
    UserService userService;

    @Autowired
    public NewcomerController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getNewcomerPage(Model model) {
        model.addAttribute("currentUser", userService.getSimpleFieldsForCurrentUser());

        return "account/newcomer";
    }
}
