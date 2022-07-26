package ru.shopper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
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

    @GetMapping
    public String getManagerPage(Model model) {
        userService.addBasicAttributes(model);

        return "account/manager";
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
}
