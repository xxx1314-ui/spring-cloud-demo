package com.itxyr.cloud.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class XTokenRequestInterceptor implements RequestInterceptor {

    /*
    * openFeign请求拦截器
    * @param requestTemplate 请求模板
    * */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        System.out.println("请求拦截器启动...........");
        requestTemplate.header("X-Token", UUID.randomUUID().toString());
    }
}
