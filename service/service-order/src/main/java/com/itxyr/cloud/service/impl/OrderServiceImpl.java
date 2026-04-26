package com.itxyr.cloud.service.impl;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.itxyr.cloud.feign.ProductFeignClient;
import com.itxyr.cloud.order.bean.Order;
import com.itxyr.cloud.product.bean.Product;
import com.itxyr.cloud.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductFeignClient productFeignClient;

   /* @Override
    public Order createOrder(Long productId, Long userId) {
        Product product = getProductFromRemoteWithLoadBalancerAnnotation(productId);
        Order order = new Order();
        order.setId(1L);
        order.setTotalAmount(product.getPrice().multiply(new BigDecimal(product.getNum())));
        order.setUserId(userId);
        order.setNickName("zhangsan");
        order.setAddress("东华理工大学");
        order.setProductList(Arrays.asList(product));
        return order;
    }*/

    /*
    * 使用FeignClient发送请求
    * */
    @SentinelResource(value = "createOrder",blockHandler = "createOrderFallback")
    @Override
    public Order createOrder(Long productId, Long userId) {
        Product product = productFeignClient.getProduct(productId);
        Order order = new Order();
        order.setId(1L);
        order.setTotalAmount(product.getPrice().multiply(new BigDecimal(product.getNum())));
        order.setUserId(userId);
        order.setNickName("zhangsan");
        order.setAddress("东华理工大学");
        order.setProductList(Arrays.asList(product));
        return order;
    }

    // 兜底回调
    public Order createOrderFallback(Long productId, Long userId, BlockException e) {
        log.error("createOrderFallback:{}", e.getMessage());
        Order order = new Order();
        order.setId(0L);
        order.setTotalAmount(new BigDecimal(0));
        order.setUserId(userId);
        order.setNickName("未知用户");
        order.setAddress("异常信息：" +e.getClass());
        order.setProductList(Arrays.asList(new Product()));
        return order;
    }


    // 没有负载均衡
    private Product getProductFromRemote(Long productId) {
        // 获取到商品服务所在的所有机器IP+port
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance instance = instances.get(0);
        // 返回URL
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/product/" + productId;
        log.info("远程请求url：{}", url);
        Product p = restTemplate.getForObject(url, Product.class);
        return p;
    }

    // 使用负载均衡第一版
    private Product getProductFromRemoteWithLoadBalancer(Long productId) {
        // 获取到商品服务所在的所有机器IP+port
        ServiceInstance instance = loadBalancerClient.choose("service-product");
        // 返回URL
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/product/" + productId;
        log.info("远程请求url：{}", url);
        Product p = restTemplate.getForObject(url, Product.class);
        return p;
    }

    // 使用负载均衡第二版(注解版本)
    private Product getProductFromRemoteWithLoadBalancerAnnotation(Long productId) {
        String url = "http://service-product/product/" + productId;
        // service-product为服务名称，会被动态替换
        Product p = restTemplate.getForObject(url, Product.class);
        return p;
    }
}
