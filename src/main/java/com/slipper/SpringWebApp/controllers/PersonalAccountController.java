package com.slipper.SpringWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/personal_account")
public class PersonalAccountController {




    @GetMapping("")
    public String showPersonalAccount(Model model) {
        return "personal_account";
    }

}
