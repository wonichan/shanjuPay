package com.emiya.nacos.consumer.controller;
/**
 * @Author: kotori
 * @Date: 2020/11/5 20:35
 * @Description:
 */

import com.eimya.microservice.service1.api.Service1Api;
import com.emiya.microservice.service2.api.Service2Api;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @ClassName RestConsumerController
 * @Description TODO
 * @Author kotori
 */
@RestController
public class RestConsumerController {
    @Value("${provider.address}")
    private String providerAddress;

    @Autowired
    LoadBalancerClient loadBalancerClient;
    /**
     * 这个注解是用来生成远程调用的代理对象
     */
    @Reference
    Service1Api service1Api;

    @Reference
    Service2Api service2Api;



    @GetMapping("/service")
    public String service(){
        RestTemplate restTemplate = new RestTemplate();
        //调用服务
        String providerResult = restTemplate.getForObject("http://"+providerAddress+"/service",String.class);
        return "consumer invoke | " + providerResult;
    }

    String serviceId = "nacos-restful-provider";
    /**
     * 通过负载均衡发现地址
     * @return
     */
    @GetMapping("/service1")
    public String service1(){
        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance instance = loadBalancerClient.choose(serviceId);
        URI uri = instance.getUri();
        String providerResult = restTemplate.getForObject(uri+"/service",String.class);
        return "consumer invoke | " + providerResult;
    }

    @GetMapping("/service2")
    public String service2(){
        //远程调用service2
        String s = service2Api.dubboService2();
        return "consumer dubbo invoke | " + s;
    }

    @GetMapping("/service3")
    public String service3(){
        //远程调用service3
        String s = service1Api.dubboService1();
        return "consumer dubbo invoke | " + s;
    }
}
