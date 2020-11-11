package com.eimya.microservice.service1.api;

/**
 * @Author: kotori
 * @Date: 2020/11/10 20:04
 * @Description:
 * 使用restful协议暴露给应用中心调用
 * 在dubbo服务之间则采用dubbo协议，速度更快
 */
public interface Service1Api {
    String dubboService1();
}
