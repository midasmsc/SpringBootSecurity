package ru.kata.spring.boot_security.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/")
    public String sayHello(ModelMap modelMap) {
//        List<String> messages = new ArrayList<>();
//        messages.add("Welcome to the CRUD Spring Boot and Security testing!");
//        messages.add("I hope it still works...");
//        messages.add("Click the button to login!");
//        modelMap.addAttribute("messages", messages);
        return "hello";
    }
}
