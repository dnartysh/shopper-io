package ru.shopper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shopper.service.StorekeeperService;
import ru.shopper.service.UserService;

@Controller
@RequestMapping("/storekeeper")
public class StorekeeperController {
    UserService userService;
    StorekeeperService storekeeperService;

    @Autowired
    public StorekeeperController(UserService userService, StorekeeperService storekeeperService) {
        this.userService = userService;
        this.storekeeperService = storekeeperService;
    }

    @GetMapping("/storage")
    public String getStoragePage(Model model) {
        userService.addBasicAttributes(model);

        return "repo/storage";
    }

    @GetMapping("/entry")
    public String getEntryPage(Model model) {
        userService.addBasicAttributes(model);

        return "doc/entry";
    }

    @GetMapping("/dispatch")
    public String getDispatchPage(Model model) {
        userService.addBasicAttributes(model);

        return "doc/dispatch";
    }
}
