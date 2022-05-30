package ru.dnartysh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AppController {

    @GetMapping("/seller")
    public String seller() {
        return "seller";
    }

    @GetMapping("/storekeeper")
    public String storekeeper() {
        return "storekeeper";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}