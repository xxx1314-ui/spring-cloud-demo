package com.itxyr.cloud.feign;

import com.itxyr.cloud.feign.fallback.ProductFeignClientFallback;
import com.itxyr.cloud.product.bean.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-product",fallback = ProductFeignClientFallback.class) // 声明FeignClient客户端
public interface ProductFeignClient {


    @GetMapping("/product/{id}")
    Product getProduct(@PathVariable("id") Long id);
}
