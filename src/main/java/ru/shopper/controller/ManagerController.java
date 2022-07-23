package ru.shopper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shopper.service.ManagerService;
import ru.shopper.service.UserService;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    UserService userService;
    ManagerService managerService;

    @Autowired
    public ManagerController(UserService userService, ManagerService managerService) {
        this.userService = userService;
        this.managerService = managerService;
    }

    @GetMapping("/product")
    public String getProductPage(Model model) {
        userService.addBasicAttributes(model);

        return "repo/product";
    }

    @GetMapping("/order")
    public String getOrderPage(Model model) {
        userService.addBasicAttributes(model);

        return "doc/order";
    }

    @GetMapping("/shop")
    public String getShopPage(Model model) {
        userService.addBasicAttributes(model);

        return "repo/shop";
    }

    @GetMapping("/user")
    public String getUserPage(Model model) {
        userService.addBasicAttributes(model);
        model.addAttribute("users", userService.getAllUsers());

        return "repo/user";
    }
}
