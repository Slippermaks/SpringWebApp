package com.slipper.SpringWebApp.services;

import com.slipper.SpringWebApp.entities.OrderItem;
import com.slipper.SpringWebApp.repositories.OrderItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Сервис продуктов в заказе
@Service
public class OrderItemsService {
    OrderItemsRepository orderItemsRepository;

    // Привязка репозитория продуктов в заказе
    @Autowired
    public void setOrderItemsRepository(OrderItemsRepository orderItemsRepository) {
        this.orderItemsRepository = orderItemsRepository;
    }

    // Получение всех продуктов по заказам
    public List<OrderItem> getAllOrderItems() { return orderItemsRepository.findAll(); }

    public List<Long> getAllIdByProductId(Long id) {
        return orderItemsRepository.findAllByProductId(id);
    }

    // Удаление продукта в заказе по ID продукта в заказе
    public void deleteOrderItemById(Long id) { orderItemsRepository.deleteById(id); }

    // Удаление всех продуктов в заказе по ID заказа
    public void deleteOrderItemsByOrderId(Long id) {
        orderItemsRepository.deleteAllByOrderId(id);
    }

    // Получение количества продуктов в заказе по ID заказа
    public Integer getCountByOrderId(Long id) {
        return orderItemsRepository.countByOrderId(id);
    }

}
