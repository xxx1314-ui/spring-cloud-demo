package com.itxyr.cloud.storage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.itxyr.cloud.storage.mapper")
public class SeataStorageApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeataStorageApplication.class, args);
    }
}