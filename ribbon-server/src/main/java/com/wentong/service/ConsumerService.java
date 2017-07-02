package com.wentong.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by zhouwentong on 2017/7/2.
 */
@Service
public class ConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerService.class);

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "addFallback")
    public String add() {
        return restTemplate.getForEntity("http://compute-service/add?first=10&second=20", String.class).getBody();
    }

    public String addFallback(Throwable throwable) {
        LOGGER.error(throwable.getMessage());
        return "服务正忙，请稍后重试";
    }
}
