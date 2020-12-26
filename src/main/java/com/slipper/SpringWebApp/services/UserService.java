package com.slipper.SpringWebApp.services;

import com.slipper.SpringWebApp.entities.User;
import com.slipper.SpringWebApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Сервис пользователей
@Service
public class UserService {
    UserRepository userRepository;

    // Привязка репозитория пользователей
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Получение всех пользователей
    public List<User> getAllUsers() { return userRepository.findAll(); }

    public User getUserByUsername(String username) { return userRepository.findOneByUsername(username); }

    // Удаление пользователя по имени
    public void deleteUserByUsername(String username) {
        userRepository.deleteByUsername(username);
    }

    // Разблокировка пользователя по имени
    public void enableUserByUsername(String username) {
        User user = userRepository.findOneByUsername(username);
        user.setEnabled(true);
        userRepository.save(user);
    }

    // Блокировка пользователя по имени
    public void disableUserByUsername(String username) {
        User user = userRepository.findOneByUsername(username);
        user.setEnabled(false);
        userRepository.save(user);
    }

}
