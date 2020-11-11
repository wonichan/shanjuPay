package com.eimya.microservice.service1.service;
/**
 * @Author: kotori
 * @Date: 2020/11/10 20:08
 * @Description:
 * 1、引入spring-cloud-starter-alibaba-nacos-discovery nacos客户端依赖
 * 2、引入spring-cloud-starter-dubbo dubbo服务依赖
 * 3、引入自己的api依赖
 * 4、引入需要调用的服务api依赖 比如service2
 * service注解不能使用spring的，用下面那个
 */


import com.eimya.microservice.service1.api.Service1Api;
import com.emiya.microservice.service2.api.Service2Api;
import org.apache.dubbo.config.annotation.Reference;


/**
 * @ClassName ServiceApiImpl
 * @Description TODO
 * @Author kotori
 */
@org.apache.dubbo.config.annotation.Service
public class Service1ApiImpl implements Service1Api {

    @Reference
    Service2Api service2Api;

    @Override
    public String dubboService1() {
        return "dubboService1 invoke |" + service2Api.dubboService2();
    }
}
