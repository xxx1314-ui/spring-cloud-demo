package com.itxyr.cloud.order.bean;

import com.itxyr.cloud.product.bean.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Order {
    private Long id; // 订单id
    private BigDecimal totalAmount; // 订单总金额
    private Long userId; // 用户id
    private String nickName; // 用户昵称
    private String address; // 收货地址
    private List<Product> productList; // 订单商品列表
}
