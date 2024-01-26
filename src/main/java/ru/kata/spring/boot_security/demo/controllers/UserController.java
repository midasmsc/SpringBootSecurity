package ru.kata.spring.boot_security.demo.controllers;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.Dao.UserDaoImpl;
import ru.kata.spring.boot_security.demo.model.User;


@Controller
public class UserController {
    private final UserDaoImpl userDaoImpl;

    @Autowired
    public UserController(UserDaoImpl userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
    }

    @GetMapping("/user")
    public String userPage(Model model, Principal principal) {
        User user = userDaoImpl.findByUserName(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }
}
