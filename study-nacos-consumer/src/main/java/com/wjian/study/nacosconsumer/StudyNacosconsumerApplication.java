package com.wjian.study.nacosconsumer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author wangjian
 * @date 2020/5/28 0028 16:06
 */
//pom导入切换 eureka-client
//@EnableEurekaClient
//pom导入切换 nacos,zookeeper,consule
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages = "com.wjian.study.nacosconsumer.mapper")
public class StudyNacosconsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyNacosconsumerApplication.class, args);
    }

}
