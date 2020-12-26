package com.slipper.SpringWebApp.repositories;

import com.slipper.SpringWebApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Интерфейс репозитория пользователей
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    // Поиск пользователя по имени пользователя
    User findOneByUsername(String username);
    //User findByUsername(String username); // Так тоже можно.

    // Удаление пользователя по имени пользователя
    void deleteByUsername(String username);
}
