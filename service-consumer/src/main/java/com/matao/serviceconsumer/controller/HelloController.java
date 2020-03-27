package com.matao.serviceconsumer.controller;

import com.matao.serviceconsumer.feign.HelloFeignClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
public class HelloController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HelloFeignClient helloFeignClient;

    /**
     * 去调用producer服务
     * @param name
     * @return
     */
    @HystrixCommand(fallbackMethod = "hystrixFallbackMethod")
    @RequestMapping("hello")
    public String hello(@RequestParam("name") String name) {
        //return "hello," + name + ",from consumer";
        /** service-producer服务名称，是application.properties文件定义的
         spring.application.name的名字 **/
        //return restTemplate.getForObject("http://service-producer/producer/hello?name="+name,String.class);
        return helloFeignClient.hello(name);
    }

    /**
     * 测试是否调用到consumer服务
     */
    @RequestMapping("/test")
    public String test(){
        return "这是conusmer服务";
    }

    public String hystrixFallbackMethod(String name){
        return "hystrixFallbackMethod调用失败："+name;
    }
}
