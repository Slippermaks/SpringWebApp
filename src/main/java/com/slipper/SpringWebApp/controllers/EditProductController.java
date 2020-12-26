package com.slipper.SpringWebApp.controllers;

import com.slipper.SpringWebApp.entities.Product;
import com.slipper.SpringWebApp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// Контроллер изменения информации о продукте в БД
@Controller
public class EditProductController {

    // Привязка сервиса продуктов
    @Autowired
    ProductService productService;

    // Показ страницы изменения информации о продукте GET
    @GetMapping("/edit_product/{id}")
    public String editProductPage(Model model, @PathVariable("id") Long id) {
        Product selectedProduct = productService.getProductById(id);
        model.addAttribute("selectedProduct", selectedProduct);
        model.addAttribute("product", new Product());
        return "/edit_product";
    }

    // Изменение информации о продукте в БД
    @PostMapping("/edit_product/{id}")
    public String updateProductSubmit(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/shop";
    }
}
