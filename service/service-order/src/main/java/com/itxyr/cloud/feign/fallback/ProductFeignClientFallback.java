package com.itxyr.cloud.feign.fallback;
import java.math.BigDecimal;

import com.itxyr.cloud.feign.ProductFeignClient;
import com.itxyr.cloud.product.bean.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductFeignClientFallback implements ProductFeignClient {
    @Override
    public Product getProduct(Long id) {
        System.out.println("兜底回调........");
        Product product = new Product();
        product.setId(id);
        product.setPrice(new BigDecimal("0"));
        product.setProductName("未知商品");
        product.setNum(0);
        return product;
    }
}
