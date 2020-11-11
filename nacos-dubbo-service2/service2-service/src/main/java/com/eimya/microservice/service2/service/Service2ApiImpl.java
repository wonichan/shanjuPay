package com.eimya.microservice.service2.service;
/**
 * @Author: kotori
 * @Date: 2020/11/10 19:17
 * @Description:
 * 1、引入spring-cloud-starter-alibaba-nacos-discovery nacos客户端依赖
 * 2、引入spring-cloud-starter-dubbo dubbo服务依赖
 * 3、引入自己的api依赖
 * service注解不能使用spring的，用下面那个
 */

import com.emiya.microservice.service2.api.Service2Api;

/**
 * @ClassName Service2ApiImpl
 * @Description TODO
 * @Author kotori
 */
@org.apache.dubbo.config.annotation.Service
public class Service2ApiImpl implements Service2Api {
    @Override
    public String dubboService2() {
        return "dubboService2";
    }
}
