package com.wjian.study.study.nacosorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class StudyNacosOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyNacosOrderApplication.class, args);
    }

}
