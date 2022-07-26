package ru.shopper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shopper.service.SellerService;
import ru.shopper.service.UserService;

@Controller
@RequestMapping("/seller")
public class SellerController {
    UserService userService;
    SellerService sellerService;

    @Autowired
    public SellerController(UserService userService, SellerService sellerService) {
        this.userService = userService;
        this.sellerService = sellerService;
    }

    @GetMapping
    public String getSellerPage(Model model) {
        userService.addBasicAttributes(model);

        return "account/seller";
    }

    @GetMapping("/shop")
    public String getShopPage(Model model) {
        userService.addBasicAttributes(model);

        return "repo/shop";
    }

    @GetMapping("/sell")
    public String getSellPage(Model model) {
        userService.addBasicAttributes(model);

        return "doc/sell";
    }
}
