package com.slipper.SpringWebApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Контроллер админки
@Controller
public class AdminController {

    // Показ страницы админки
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }


}