package com.slipper.SpringWebApp.controllers;

import com.slipper.SpringWebApp.entities.Authority;
import com.slipper.SpringWebApp.entities.User;
import com.slipper.SpringWebApp.repositories.AuthorityRepository;
import com.slipper.SpringWebApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// Контроллер регистрации
@Controller
public class RegistrationController {

    // Привязка репозитория пользователей
    @Autowired
    private UserRepository userRepository;

    // Привязка репозитория ролей
    @Autowired
    private AuthorityRepository authorityRepository;

    // Показ страницы регистрации GET
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    // Сохранение пользователя в БД
    @PostMapping("/registration")
    public String registration(User user, Model model) {
        User userFromDB = userRepository.findOneByUsername(user.getUsername()); // Поиск на уже существующего пользователя с текущим логином

        if (userFromDB != null) { // Если такой пользователь уже существует
            model.addAttribute("message", "User already exist!");
            System.out.println("already exist");
            return "/registration"; // Вернуть на страницу регистрации
        }

        user.setEnabled(true); // Иначе разбанить пользователя
        Authority authority = new Authority(user.getUsername(), "ROLE_USER"); // Присвоить роль пользователя
        userRepository.save(user); // Сохранить пользователя
        authorityRepository.save(authority); // Сохранить ему роль

        return "redirect:/login";
    }
}
