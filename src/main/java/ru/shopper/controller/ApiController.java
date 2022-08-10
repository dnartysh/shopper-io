package ru.shopper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shopper.service.AppService;
import ru.shopper.service.UserService;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/api")
public class ApiController {
    AppService appService;
    UserService userService;

    @Autowired
    public ApiController(AppService appService, UserService userService) {
        this.appService = appService;
        this.userService = userService;
    }

    @GetMapping("/chart/user")
    public String getNewUsersCount() {
        Collection<Integer> collection = new ArrayList<>();
        collection.add(0);
        collection.add(5);
        collection.add(10);
        collection.add(16);
        collection.add(2);
        collection.add(32);

        return collection.toString();
    }

    @GetMapping("/chart/user/period")
    public String getUserByPosition() {
        Collection<Integer> collection = new ArrayList<>();
        collection.add(2);
        collection.add(3);
        collection.add(1);
        collection.add(0);

        return collection.toString();
    }

    @GetMapping("/chart/server/memory")
    public String getServerMemoryUsage() {


        return "HGWEWEH";
    }
}
