package com.itxyr.cloud.service;


import com.itxyr.cloud.product.bean.Product;

public interface ProductService {
    Product getProductById(Long id);
}
