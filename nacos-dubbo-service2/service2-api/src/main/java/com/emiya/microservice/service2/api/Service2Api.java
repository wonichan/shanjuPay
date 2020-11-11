package com.emiya.microservice.service2.api;

/**
 * @Author: kotori
 * @Date: 2020/11/10 19:14
 * @Description:
 *
 * 使用restful协议暴露给应用中心调用
 * 在dubbo服务之间则采用dubbo协议，速度更快
 */
public interface Service2Api {
    String dubboService2();
}
