package com.itxyr.cloud.service.impl;
import java.math.BigDecimal;

import com.itxyr.cloud.product.bean.Product;
import com.itxyr.cloud.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Product getProductById(Long id) {
        Product product = new Product();
        product.setId(id);
        product.setPrice(new BigDecimal("99"));
        product.setProductName("苹果"+id);
        product.setNum(2);
        return product;
    }
}
