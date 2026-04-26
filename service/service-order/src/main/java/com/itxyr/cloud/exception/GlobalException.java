package com.itxyr.cloud.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@ResponseBody
//@ControllerAdvice // 全局异常处理
@RestControllerAdvice // 全局异常处理
public class GlobalException {
    /*@ExceptionHandler(Throwable.class)
    public String handleException(Throwable e) {
        e.printStackTrace();
        return "服务器异常";
    }*/
}
