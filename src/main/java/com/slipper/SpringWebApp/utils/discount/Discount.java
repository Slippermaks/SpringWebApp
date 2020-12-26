package com.slipper.SpringWebApp.utils.discount;

import com.slipper.SpringWebApp.entities.OrderItem;

import java.util.List;

public interface Discount {

    List<OrderItem> getDiscountedItems(List<OrderItem> items);
}
