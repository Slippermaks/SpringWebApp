package com.slipper.SpringWebApp.controllers;

import com.slipper.SpringWebApp.entities.Order;
import com.slipper.SpringWebApp.entities.OrderItem;
import com.slipper.SpringWebApp.entities.Product;
import com.slipper.SpringWebApp.services.OrderService;
import com.slipper.SpringWebApp.utils.ShoppingCart;
import com.slipper.SpringWebApp.utils.discount.Discount;
import com.slipper.SpringWebApp.utils.discount.DiscountFactory;
import com.slipper.SpringWebApp.utils.discount.DiscountTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Контроллер корзины
@Controller
@RequestMapping("/cart")
public class CartController {
    private ShoppingCart cart;

    private OrderService orderService;

    private DiscountTypes type;

    // Привязка сервиса заказов
    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    // Привязка корзины
    @Autowired
    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    // Показ страницы корзины пользователя
    @GetMapping("")
    public String showCart(Model model) {
        model.addAttribute("items", cart.getItems());

        Integer totalCost = calculateTotalCost(cart.getItems());
        model.addAttribute("totalCost", totalCost);

        return "cart";
    }

    public Integer calculateTotalCost(List<OrderItem> items) {
        Integer totalCost = 0; // Подсчёт итоговой стоимости в корзине
        for (OrderItem item : items) {
            totalCost += item.getProduct().getPrice();
        }
        return totalCost;
    }

    // Добавление продукта в корзину
    @GetMapping("/add/{id}")
    public String addProductToCart(Model model, @PathVariable("id") Long id) {
        cart.addProductById(id);
        model.addAttribute("items", cart.getItems());
        return "redirect:/shop";
    }

    // Удаление продукта из корзины
    @GetMapping("/delete/{index}")
    public String deleteProductFromCart(@PathVariable("index") int index) {
        System.out.println(index);
        cart.getItems().remove(index);
        return "redirect:/cart";
    }

    @GetMapping("/applyDiscount")
    public String applyDiscount(Model model) {
        List<OrderItem> discountedItems = getDiscountedItems();
        model.addAttribute("items", discountedItems);

        Integer totalCost = calculateTotalCost(discountedItems);
        model.addAttribute("totalCost", totalCost);

        for (OrderItem itemCart : cart.getItems()) {
            for (OrderItem itemDiscount : discountedItems) {
                if (itemCart.getProduct().getId() == itemDiscount.getProduct().getId()) {
                    itemCart.setTotalPrice(itemDiscount.getProduct().getPrice());
                }
            }
        }

        model.addAttribute("discountType", type);

        return "cart";
    }

    public List<OrderItem> getDiscountedItems() {
        DiscountFactory factory = new DiscountFactory();
        Discount discount = getRandomDiscount(factory);
        return discount.getDiscountedItems(cart.getItems());
    }

    public Discount getRandomDiscount(DiscountFactory factory) {
        Random rnd = new Random();
        type = DiscountTypes.values()[rnd.nextInt(DiscountTypes.values().length)];
        System.out.println(type);
        return factory.getDiscount(type);
    }

    // Оформление заказа
    @GetMapping("/create_order")
    public String create_order(Principal principal) {
        Order order = new Order(); // Инициализация нового заказа
        order.setItems(new ArrayList<>());
        order.setUsername(principal.getName()); // Привязка заказа к текущему пользователю

        for (int i=0; i<cart.getItems().size(); i++) {

        }

        cart.getItems().stream().forEach(i -> { // Добавление товаров из корзины в заказ
            order.getItems().add(i);
            i.setOrder(order);
        });
        cart.getItems().clear(); // Очистка корзины
        orderService.saveOrder(order); // Сохранение заказа

        return "redirect:/shop";
    }
}
