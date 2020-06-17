package com.wjian.study.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangjian
 * @date 2020/6/12 0012 8:45
 */
@Configuration
public class LoadBalanceConfig {

    @Bean
    public IRule loadBalance(){
        return new RandomRule();
    }
}
