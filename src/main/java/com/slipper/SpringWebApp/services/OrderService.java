package com.slipper.SpringWebApp.services;

import com.slipper.SpringWebApp.entities.Order;
import com.slipper.SpringWebApp.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Сервис заказа
@Service
public class OrderService {
    private OrderRepository orderRepository;

    // Привязка репозитория заказов
    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Сохранение заказа
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    // Получение заказа по имени пользователя
    public Order getOrderByUsername(String username) {
        return orderRepository.getByUsername(username);
    }

    // Удаление заказа по имени пользователя
    public void deleteOrderByUsername(String username) {
        orderRepository.deleteByUsername(username);
    }

    // Удаление заказа по ID
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }
}
