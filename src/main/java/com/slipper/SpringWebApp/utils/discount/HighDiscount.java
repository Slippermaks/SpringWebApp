package com.slipper.SpringWebApp.utils.discount;

import com.slipper.SpringWebApp.entities.OrderItem;
import com.slipper.SpringWebApp.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class HighDiscount implements Discount { // (500-1000]


    @Override
    public List<OrderItem> getDiscountedItems(List<OrderItem> items) {
        List<OrderItem> discountedItems = new ArrayList<>();

        for (OrderItem item : items) {
            OrderItem discountedItem = item;
            if (item.getProduct().getPrice() > 500 && item.getProduct().getPrice() <= 1000) {
                discountedItem.getProduct().setPrice((int) (item.getProduct().getPrice() * 0.9));
            }
            discountedItems.add(discountedItem);
        }

        return discountedItems;
    }
}
