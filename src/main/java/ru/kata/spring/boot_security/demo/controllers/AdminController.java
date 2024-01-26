package ru.kata.spring.boot_security.demo.controllers;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/")
    public String getAllUsers(ModelMap model, Principal principal) {
        User user = userService.findByUserName(principal.getName());
        model.addAttribute("user", user);
        List<User> listOfUsers = userService.getAllUsers();
        model.addAttribute("listOfUsers", listOfUsers);
        return "users";
    }

    @GetMapping("/new")
    public String CreateUserForm(@ModelAttribute("user") @Valid User user, ModelMap model) {
        model.addAttribute("roles", roleService.getRoles());
        return "new";
    }

    @PostMapping("/")
    public String addUser(@ModelAttribute("user") @Valid User user, @RequestParam("role") Long roleId) {
        Role role = roleService.findById(roleId);
        user.setRoles(Collections.singleton(role));
        userService.addUser(user);
        return "redirect:/admin/";
    }

    @GetMapping("/{id}/update")
    public String getEditUserForm(ModelMap model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUser(id));
        return "updateInfo";
    }

    @PatchMapping("/{id}")
    public String saveUpdateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id,
                                 @RequestParam("roles") List<String> roles) {
        Set<Role> selectedRoles = roles.stream()
                .map(roleService::findByName)
                .collect(Collectors.toSet());
        user.setRoles(selectedRoles);
        userService.updateUser(user);
        return "redirect:/admin/";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return "redirect:/admin/";
    }
}
