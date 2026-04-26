package com.itxyr.cloud;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@SpringBootApplication
@EnableDiscoveryClient //开启服务注册与发现
@EnableFeignClients // 开启feign远程调用功能
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class);
    }


    // 1.项目启动就监听配置文件变化
    // 2.发生变化的时候执行方法
    // 3. 发送邮件
    @Bean
    ApplicationRunner runner(NacosConfigManager manager) {
        return args -> {
            System.out.println("===========");
            ConfigService service = manager.getConfigService();
            service.addListener("service-order.properties", "DEFAULT_GROUP", new Listener() {
                @Override
                public Executor getExecutor() {
                    return Executors.newFixedThreadPool(4);
                }

                @Override
                public void receiveConfigInfo(String s) {
                    System.out.println("变化的配置信息：" + s);
                    System.out.println("邮件通知...");
                }
            });
        };
    }
}
