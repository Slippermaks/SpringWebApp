package com.slipper.SpringWebApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// Контроллер главной страницы
@Controller
public class MainController {
    // http://localhost:8189/app/index

    // Показ главной страницы
    @GetMapping("/index")
    public String homePage(Model model) {
        return "index";
    }




    @GetMapping("/data")
    @ResponseBody
    public String dataExample(@RequestParam(value = "serial", required = false) Long serial, @RequestParam("number") Long number) {
        return "S/N: " + serial + " / " + number;
    }
}
