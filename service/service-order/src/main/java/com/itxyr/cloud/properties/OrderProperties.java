package com.itxyr.cloud.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "order") // 配置批量绑定在nacos下，可以无需@RefreshScope注解就能实现自动刷新
@Component
public class OrderProperties {
    String timeout;
    String autoConfirm;
    String dbUrl;
}
