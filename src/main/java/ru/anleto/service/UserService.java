package ru.anleto.service;

import ru.anleto.model.Role;
import ru.anleto.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    User updateUser(User user);

    void deleteUser(Long id);

    User getUserByName(String login);

    Role getRoleByName(String name);

    List<Role> getAllRoles();

    void addRole(Role role);
}
