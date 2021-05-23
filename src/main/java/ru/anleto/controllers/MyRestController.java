package ru.anleto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.anleto.model.User;
import ru.anleto.service.UserService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class MyRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/newUser")
    public User newUser(@Valid @RequestBody User user) {
        User user1 = user;
        return userService.updateUser(user1);
    }

    @PutMapping("/edit")
    public User updateUser(@Valid @RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }
}
