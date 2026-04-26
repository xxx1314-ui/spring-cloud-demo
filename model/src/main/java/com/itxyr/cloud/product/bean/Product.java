package com.itxyr.cloud.product.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private Long id; // 商品id
    private BigDecimal price; // 价格
    private String productName; // 商品名称
    private int num; // 数量
}
