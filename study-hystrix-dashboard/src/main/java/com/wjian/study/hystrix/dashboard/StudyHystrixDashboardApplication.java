package com.wjian.study.hystrix.dashboard;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author wangjian
 * @date 2020/5/28 0028 16:06
 */
@SpringBootApplication
//http://localhost:8765/hystrix
@EnableHystrixDashboard
public class StudyHystrixDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyHystrixDashboardApplication.class, args);
    }
}
