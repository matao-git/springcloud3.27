package com.matao.serviceconsumer.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 创建配置类
 */
@Configuration
public class RibbonConfig {
    /**
     * 添加@LoadBalanced注解，resTemplate实现负载均衡
     *
     * @return
     */
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 配置负载均衡算法
     *
     * @return
     */
    @Bean
    public IRule iRule() {
        /**
         * 随机选择一个服务，进行访问
         */
        return new RandomRule();
    }

}
