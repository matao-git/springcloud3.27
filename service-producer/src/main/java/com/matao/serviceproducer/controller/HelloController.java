package com.matao.serviceproducer.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/producer")
public class HelloController {

    /**
     * 供应consumer服务调用
     * @param name
     * @return
     */
    @RequestMapping("/hello")
    public String hello(@RequestParam("name") String name){
        System.out.println("hello ------ 这是日志");
        return "hello,"+name+",from producer producer->consumer";
    }

    /**
     * 测试producer服务
     * @return
     */
    @RequestMapping("/test")
    public String test(){
        return "这是producer服务";
    }
}
