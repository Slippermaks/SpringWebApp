package com.slipper.SpringWebApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Контроллер логина
@Controller
public class LoginController {

    // Показ страницы логина
    @GetMapping("/login")
    public String login() {
        return "login";
    }


}