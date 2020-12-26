package com.slipper.SpringWebApp.repositories;

import com.slipper.SpringWebApp.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Интерфейс репозитория продуктов в заказе
@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItem, Long> {

    // Удаление всех продуктов в заказе по ID заказа
    void deleteAllByOrderId(Long id);

    // Подсчёт количества продуктов в заказе по ID заказа
    Integer countByOrderId(Long id);

    // Список всех ИД заказов, в которых есть продукт с ID (id)
    List<Long> findAllByProductId(Long id);
}
