package com.example.hpwin8.smartsupermarket.Info;

import com.google.gson.GsonBuilder;

import java.util.List;

/**
 * Created by HP WIN 8 on 7/1/2017.
 */

public class Order {
    public long UserId;
    public List<OrderItem> orderItems;
    public Order(long _userId,List<OrderItem> _orderItems) {
        UserId = _userId;
        orderItems = _orderItems;
    }
    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, Order.class);
    }
}
