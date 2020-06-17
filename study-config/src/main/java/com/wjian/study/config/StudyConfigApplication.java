package com.wjian.study.config;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import com.wjian.study.rule.LoadBalanceConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @author wangjian
 * @date 2020/5/28 0028 16:06
 */
//pom导入切换 eureka-client
//@EnableEurekaClient
//pom导入切换 nacos,zookeeper,consule
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages = "com.wjian.study.config.mapper")
//指定访问策略 - 对单一服务，扫描的类在scan之外，否则全局生效
@RibbonClient(name = "study-nacos-order",configuration = LoadBalanceConfig.class)
@EnableFeignClients
@EnableCircuitBreaker
@EnableConfigServer
public class StudyConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyConfigApplication.class, args);
    }

}
