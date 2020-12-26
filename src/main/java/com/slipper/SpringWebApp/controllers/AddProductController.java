package com.slipper.SpringWebApp.controllers;

import com.slipper.SpringWebApp.entities.Product;
import com.slipper.SpringWebApp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// @Controller - специальный тип класса, применяемый в MVC приложениях.
// Обрабатывает запросы и часто используется с аннотацией @RequestMapping.

// Контроллер добавления нового продукта в БД
@Controller
@RequestMapping("/admin/add_product")
public class AddProductController {

    // Привязка сервиса продуктов
    @Autowired
    ProductService productService;

    // Показ формы добавления нового продукта GET
    @GetMapping("")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add_product";
    }

    // Отправка POST запроса и сохранение данных в БД
    @PostMapping("")
    public String addProductSubmit(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/admin";
    }
}
