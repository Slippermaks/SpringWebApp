package com.slipper.SpringWebApp.controllers;

import com.slipper.SpringWebApp.entities.Product;
import com.slipper.SpringWebApp.services.OrderItemsService;
import com.slipper.SpringWebApp.services.OrderService;
import com.slipper.SpringWebApp.services.ProductService;
import com.slipper.SpringWebApp.utils.discount.Discount;
import com.slipper.SpringWebApp.utils.discount.DiscountFactory;
import com.slipper.SpringWebApp.utils.discount.DiscountTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Controller
@RequestMapping("/shop")
public class ShopController {

    private ProductService productService;
    private OrderItemsService orderItemsService;
    private OrderService orderService;

    String msg;

    // Привязка сервиса продуктов
    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setOrderItemsService(OrderItemsService orderItemsService) {
        this.orderItemsService = orderItemsService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    // Показ страницы продуктов по умолчанию
    @GetMapping("")
    public String shopPage(Model model) {
        return sortedPage(model, "title", "asc");
    }

    // Показ страницы продуктов отсортированной
    @GetMapping("/sortedPage")
    public String sortedPage(Model model,
                             @RequestParam("sortField") String sortField,
                             @RequestParam("sortDir") String sortDir) {
        List<Product> allProducts = productService.getSorted(sortField, sortDir);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("products", allProducts);
        //model.addAttribute("discountedProducts", null);
        model.addAttribute("msg", msg);
        msg = "";
        return "shop";
    }

    // Показ страницы с деталями продукта
    @GetMapping("/details/{id}")
    public String detailsPage(Model model, @PathVariable("id") Long id) {
        Product selectedProduct = productService.getProductById(id);
        model.addAttribute("selectedProduct", selectedProduct);
        return "details";
    }

    // Поиск продукта по названию
    @GetMapping("/find_by_title")
    public String detailsPageByTitle(Model model, @RequestParam("title") String title) {
        Product selectedProduct = productService.getProductByTitle(title);
        model.addAttribute("selectedProduct", selectedProduct);
        return "details";
    }

    // Удаление продукта
    @GetMapping("/products/delete/{id}")
    public String deleteProductById(Model model, @PathVariable("id") Long id) {

        msg = "";
        try {
            productService.deleteProductById(id);
        } catch (Exception ex) {
            msg = "Существует заказ на текущий продукт. Обработайте заказ, затем завершите удаление продукта.";
        }

        return "redirect:/shop";
    }


}
