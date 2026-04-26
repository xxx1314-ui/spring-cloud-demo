package com.itxyr.cloud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

@SpringBootTest
public class LoadBalancerTest {

    @Autowired
    private LoadBalancerClient loadBalancerClient;


    @Test
    void testLoadBalancer() {
        for (int i = 0; i < 10; i++) {
            // 默认随机轮询
            ServiceInstance serviceInstance = loadBalancerClient.choose("service-product");
            System.out.println("第" + i + "次访问：" + serviceInstance.getHost() + ":" + serviceInstance.getPort());
        }
    }
}
