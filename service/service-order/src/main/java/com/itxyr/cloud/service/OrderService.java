package com.itxyr.cloud.service;


import com.itxyr.cloud.order.bean.Order;

public interface OrderService {
    Order createOrder(Long productId, Long userId);
}
