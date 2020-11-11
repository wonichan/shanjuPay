package com.emiya.nacos.consumer.controller;
/**
 * @Author: kotori
 * @Date: 2020/11/5 20:35
 * @Description:
 * 不管是消费方还是生产方，都要将自己的服务注册到nacos服务中心去
 * 引入spring-cloud-starter-alibaba-nacos-discovery依赖
 * 1、将自己的地址注册到服务发现中心 2、从服务发现中心获取服务列表
 *
 * dubbo服务调用：
 * 1、先引入调用的api依赖
 * 2、再引入spring-cloud-starter-dubbo依赖
 * 3、使用@Reference 即可生成dubbo服务的代理对象
 * 4、调用api
 *
 * 发布配置：将配置信息发布到nacos配置中心
 * 获取配置：配置中心客户端得到配置中心的通知，从配置中心获取配置
 * 具体获取什么配置 参考bootstrap.yml文件
 * 服务的配置文件不能使用application作为配置文件名，
 * 得使用bootstrap.yml  因为bootstrap的优先级高于application
 * 主要作用是为了在加载获取配置之前我们要先配置好自己服务的一些配置
 */

import com.eimya.microservice.service1.api.Service1Api;
import com.emiya.microservice.service2.api.Service2Api;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.ConfigurableApplicationContext;
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
        //客户端负载均衡
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

    /**
     * Value注解不具备更新配置信息的能力，只能读
     */
    @Value("${common.name}")
    private String common_name;

    /**
     * 可以自动更新配置信息
     */
    @Autowired
    ConfigurableApplicationContext applicationContext;

    @GetMapping("/config")
    public String getvalue(){
        return applicationContext.getEnvironment().getProperty("common.name");
//        return common_name;
    }

    @GetMapping("/configs")
    public String getConfigs(){
        /**
         * 如果主配置文件和扩展配置文件有属性重名
         * 则以主配置文件为准
         * 且对于扩展配置文件 在bootstrap配置文件中的ext-config[1]
         * 数组下标越大，优先级越高
         */
        String name = applicationContext.getEnvironment().getProperty("common.name");
        String addr = applicationContext.getEnvironment().getProperty("common.addr");
        return name + " : " + addr;
    }
}
