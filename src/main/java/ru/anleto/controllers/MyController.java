package ru.anleto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.anleto.model.User;

@Controller
@RequestMapping("/")
public class MyController {

    @GetMapping(value = "/admin")
    public String showAllUsers() {
        return "users";
    }

    @GetMapping(value = "/user")
    public String user() {
        return "hello";
    }

    @PatchMapping(value = "/{id}")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam(value = "select_roles", required = false) String[] role) {

        return "redirect:/admin";
    }
}
