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

    @GetMapping("/shop")
    public String getShopPage(Model model) {
        model.addAttribute("currentUser", userService.getSimpleFieldsForCurrentUser());

        return "repo/shop";
    }

    @GetMapping("/sell")
    public String getSellPage(Model model) {
        model.addAttribute("currentUser", userService.getSimpleFieldsForCurrentUser());

        return "doc/sell";
    }
}
