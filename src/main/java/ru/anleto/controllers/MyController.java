package ru.anleto.controllers;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.anleto.model.Role;
import ru.anleto.model.User;
import ru.anleto.service.UserService;


import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("")
public class MyController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/admin")
    public String showAllUsers(ModelMap model) {
        model.addAttribute("allRoles", userService.getAllRoles());
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("addUser", new User());
        return "users";
    }

//    @GetMapping(value = "/user")
//    public String user() {
//        return "hello";
//    }

    @GetMapping(value = "/user")
    public String printUserInfo(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", userService.getUserByName(auth.getName()));

        return "hello";
    }


    @PostMapping(value = "/admin")
    public String addNewUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult,
                             @RequestParam("role") String[] role) {
        if (bindingResult.hasErrors())
            return "addUser";
        Set<Role> roleSet = new HashSet<>();
        for (String roles : role) {
            roleSet.add(userService.getRoleByName(roles));
        }
        user.setRoles(roleSet);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PatchMapping(value = "/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/{id}")
    public String removeUser(@PathVariable("id") String id) {
        System.out.println(id);
        System.out.println(id);
        System.out.println(id);
        userService.deleteUser(Long.parseLong(id));
        return "redirect:/admin";
    }
}
