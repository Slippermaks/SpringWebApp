package com.slipper.SpringWebApp.controllers;

import com.slipper.SpringWebApp.entities.User;
import com.slipper.SpringWebApp.services.AuthorityService;
import com.slipper.SpringWebApp.services.OrderItemsService;
import com.slipper.SpringWebApp.services.OrderService;
import com.slipper.SpringWebApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.util.List;

// Контроллер страницы списка пользователей и работы над пользователем
@Controller
@RequestMapping("/admin/user_list")
public class UserListController {

    UserService userService;
    AuthorityService authorityService;
    OrderItemsService orderItemsService;
    OrderService orderService;

    // Привязки сервисов...
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAuthorityService(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @Autowired
    public void setOrderItemsService(OrderItemsService orderItemsService) {
        this.orderItemsService = orderItemsService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    // Показ страницы списка пользователей
    @GetMapping("")
    public String showUsers(Model model) {
        List<User> allUsers = userService.getAllUsers(); // Получение списка всех пользователей
        model.addAttribute("users", allUsers);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authorizedUserUsername = auth.getName();// Получение логина аутентифицированного пользователя
        model.addAttribute("authorizedUserUsername", authorizedUserUsername);

        return "user_list";
    }

    // Удаление пользователя из БД
    @Transactional
    @GetMapping("/delete/{username}")
    public String deleteUserByUsername(@PathVariable("username") String username) {
        try {
            authorityService.deleteAuthorityByUsername(username); // Удаление роли пользователя
        } catch (NullPointerException ex) {
            System.out.println("роль нулевая");
        }

        try { // Удаление товаров в заказах пользователя
            orderItemsService.deleteOrderItemsByOrderId(orderService.getOrderByUsername(username).getId());
        } catch (NullPointerException ex) {
            System.out.println("Ордер итемов нет");
        }

        try { // Удаление заказов пользователя
            orderService.deleteOrderByUsername(username);
        } catch (NullPointerException ex) {
            System.out.println("заказов нет");
        }

        userService.deleteUserByUsername(username); // Удаление пользователя

        return "redirect:/admin/user_list";
    }

    // Блокировка пользователя
    @GetMapping("/disable/{username}")
    public String disableUserByUsername(@PathVariable("username") String username) {
        userService.disableUserByUsername(username);
        return "redirect:/admin/user_list";
    }

    // Разблокировка пользователя
    @GetMapping("/enable/{username}")
    public String enableUserByUsername(@PathVariable("username") String username) {
        userService.enableUserByUsername(username);
        return "redirect:/admin/user_list";
    }
}
