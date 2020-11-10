package com.eimya.microservice.service1.service;
/**
 * @Author: kotori
 * @Date: 2020/11/10 20:08
 * @Description:
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
