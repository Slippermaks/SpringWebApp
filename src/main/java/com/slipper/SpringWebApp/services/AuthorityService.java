package com.slipper.SpringWebApp.services;

import com.slipper.SpringWebApp.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// @Service - указывает, что класс является сервисом для реализации бизнес логики
// (на самом деле не отличается от Component, но просто помогает разработчику указать смысловую нагрузку класса).

// Сервис роли
@Service
public class AuthorityService {
    AuthorityRepository authorityRepository;

    // Привязка репозитория ролей
    @Autowired
    public void setAuthorityRepository(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    // Удаление роли по имени пользователя
    public void deleteAuthorityByUsername(String username) {
        authorityRepository.deleteByUsername(username);
    }

}
