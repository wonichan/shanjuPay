package com.emiya.nacos.provider.controller;
/**
 * @Author: kotori
 * @Date: 2020/11/5 20:19
 * @Description:
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
