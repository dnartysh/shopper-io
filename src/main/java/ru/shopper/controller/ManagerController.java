package ru.shopper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.shopper.service.ProductService;
import ru.shopper.service.UserService;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    UserService userService;
    ProductService productService;

    @Autowired
    public ManagerController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping
    public String getManagerPage(Model model) {
        userService.addBasicAttributes(model);

        return "account/manager";
    }

    @GetMapping("/product")
    public String getProductPage(Model model) {
        userService.addBasicAttributes(model);
        model.addAttribute("products", productService.getAllProducts());

        return "repo/product";
    }

    @PostMapping("/product")
    public String addProduct(Model model,
                             @RequestParam String name,
                             @RequestParam Double price,
                             @RequestParam Double weight,
                             @RequestParam String description) {
        userService.addBasicAttributes(model);
        productService.addProduct(name, price, weight, description);

        model.addAttribute("products", productService.getAllProducts());

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
