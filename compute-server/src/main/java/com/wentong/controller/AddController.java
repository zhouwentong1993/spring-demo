package com.wentong.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhouwentong on 2017/7/2.
 */
@RestController
public class AddController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AddController.class);
    

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private Registration registration;

    @GetMapping("add")
    public Integer add(@RequestParam Integer first,@RequestParam Integer second) {
        ServiceInstance localServiceInstance = client.getLocalServiceInstance();
        String serviceId = registration.getServiceId();
        System.out.println("serviceId: " + serviceId);
        System.out.println("localServiceInstance:" + localServiceInstance.getHost());

        return first + second;
    }

}
