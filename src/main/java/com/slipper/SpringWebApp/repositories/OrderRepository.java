package com.slipper.SpringWebApp.repositories;

import com.slipper.SpringWebApp.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Интерфейс репозитория заказов
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Получение заказа по имени пользователя
    Order getByUsername(String username);

    // Удаление заказа по имени пользователя
    void deleteByUsername(String username);
}
