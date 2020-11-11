package com.emiya.nacos.provider.controller;
/**
 * @Author: kotori
 * @Date: 2020/11/5 20:19
 * @Description:
 * 不管是消费方还是生产方，都要将自己的服务注册到nacos服务中心去
 * 引入spring-cloud-starter-alibaba-nacos-discovery依赖
 * 1、将自己的地址注册到服务发现中心 2、从服务发现中心获取服务列表
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RestProviderController
 * @Description TODO
 * @Author kotori
 */
@RestController
@Slf4j
public class RestProviderController {
    /**
     * 暴露服务
     * @return
     */
    @GetMapping("/service")
    public String service(){
        log.info("provicer invoke");
        return "provider invoke";
    }
}
