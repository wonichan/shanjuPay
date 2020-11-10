package com.eimya.microservice.service2.service;
/**
 * @Author: kotori
 * @Date: 2020/11/10 19:17
 * @Description:
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
