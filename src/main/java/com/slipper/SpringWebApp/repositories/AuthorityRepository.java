package com.slipper.SpringWebApp.repositories;

import com.slipper.SpringWebApp.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository - указывает, что класс используется для работы с поиском, получением и хранением данных.
// Аннотация может использоваться для реализации шаблона DAO.

// Интерфейс репозитория ролей
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {

    // Удаление роли по имени пользователя
    void deleteByUsername(String username);
}
