package com.itxyr.cloud.controller;

import com.itxyr.cloud.product.bean.Product;
import com.itxyr.cloud.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    /*
    * 根据id查询商品
    * @param id 商品id
    * */
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long id, HttpServletRequest  request) {
        String header = request.getHeader("X-Token");
        System.out.println("X-Token: " + header);
        return productService.getProductById(id);
    }
}
