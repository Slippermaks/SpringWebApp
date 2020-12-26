package com.slipper.SpringWebApp.utils;

import com.slipper.SpringWebApp.entities.OrderItem;
import com.slipper.SpringWebApp.entities.Product;
import com.slipper.SpringWebApp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

// @Component - используется для указания класса в качестве компонента spring.
// При использовании поиска аннотаций, такой класс будет сконфигурирован как spring bean.

// Корзина для продуктов
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart {
    private List<OrderItem> items; // Создание списка продуктов

    private ProductService productService;

    // Привязка сервиса продуктов
    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public List<OrderItem> getItems() {
        return items;
    }


    // Перед построением инициализировать пустой список продуктов
    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    // Добавление продукта в корзину по ID
    public void addProductById(Long id) {
        Product product = productService.getProductById(id);
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setTotalPrice(product.getPrice());
        items.add(orderItem);
    }
}
