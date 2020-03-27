package com.matao.serviceconsumer.feign;

import org.springframework.stereotype.Component;

@Component
public class HelloFeignCallback implements  HelloFeignClient{

    @Override
    public String hello(String name) {
        return "调用服务失败";
    }
}
