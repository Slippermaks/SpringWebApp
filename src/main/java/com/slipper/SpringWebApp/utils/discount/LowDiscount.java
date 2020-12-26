package com.slipper.SpringWebApp.utils.discount;


import com.slipper.SpringWebApp.entities.OrderItem;
import com.slipper.SpringWebApp.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class LowDiscount implements Discount { // [0-100]

    @Override
    public List<OrderItem> getDiscountedItems(List<OrderItem> items) {
        List<OrderItem> discountedItems = new ArrayList<>();

        for (OrderItem item : items) {
            OrderItem discountedItem = item;
            if (item.getProduct().getPrice() >= 0 && item.getProduct().getPrice() <= 100) {
                discountedItem.getProduct().setPrice((int)(item.getProduct().getPrice() * 0.5));
            }
            discountedItems.add(discountedItem);
        }

        return discountedItems;
    }
}
