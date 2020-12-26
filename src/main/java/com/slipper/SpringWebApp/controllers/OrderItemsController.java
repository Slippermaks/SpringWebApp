package com.slipper.SpringWebApp.controllers;

import com.slipper.SpringWebApp.entities.OrderItem;
import com.slipper.SpringWebApp.services.OrderItemsService;
import com.slipper.SpringWebApp.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// Контроллер товаров в заказе
@Controller
@RequestMapping("/admin/order_items")
public class OrderItemsController {

    OrderItemsService orderItemsService;
    OrderService orderService;

    // Привязка сервиса продуктов в заказе
    @Autowired
    public void setOrderItemsService(OrderItemsService orderItemsService) {
        this.orderItemsService = orderItemsService;
    }

    // Привязка сервиса заказов
    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    // Показ продукта по каждому заказу
    @GetMapping("")
    public String showOrders(Model model) {
        List<OrderItem> allProducts = orderItemsService.getAllOrderItems();
        model.addAttribute("items", allProducts);
        return "order_items";
    }

    // Удаление продукта из заказа
    @GetMapping("/delete/{id}")
    public String deleteOrderItemById(@PathVariable("id") Long id, @RequestParam("orderId") Long orderId) {
        if (orderItemsService.getCountByOrderId(orderId) == 1) { // Если количество продуктов в заказе = 1
            orderItemsService.deleteOrderItemById(id); // Удаляем продукт из заказа
            orderService.deleteOrderById(orderId); // Удаляем заказ
        } else if (orderItemsService.getCountByOrderId(orderId) > 1) { // Иначе просто удаляем продукт из заказа
            orderItemsService.deleteOrderItemById(id);
        }
        return "redirect:/admin/order_items";
    }
}
