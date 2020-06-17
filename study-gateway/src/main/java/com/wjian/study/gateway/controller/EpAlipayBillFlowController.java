package com.wjian.study.gateway.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wjian.study.domain.basic.BasicResponse;
import com.wjian.study.gateway.feign.EpAlipayBillFlowServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

//@RestController
@RefreshScope
/**
 * @author wangjian
 * @date 2020/5/28 0028 16:06
 */
 public class EpAlipayBillFlowController {

    private Logger logger  = LoggerFactory.getLogger(this.getClass());

    @Value("${config.info}")
    private String configInfo;

    @Autowired
    private EpAlipayBillFlowServer epAlipayBillFlowServer;

    @RequestMapping(value = "/EpAlipayBillFlow/{flowNo}",method = RequestMethod.GET)
    //=====服务熔断
    @HystrixCommand(fallbackMethod = "epAlipayBillFlow_circuitBreaker",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 失败率达到多少后跳闸
    })
    public BasicResponse epAlipayBillFlow(@PathVariable String flowNo){
        logger.info("/EpAlipayBillFlow/flowNo发起请求，请求参数：{}----{}",flowNo,configInfo);
        return epAlipayBillFlowServer.epAlipayBillFlow(flowNo);
    }

    public BasicResponse epAlipayBillFlow_circuitBreaker(String flowNo){
        logger.info("服务时间窗口期中，请求次数达规定次数，切阀值失败率达到预定制：{}----{}",flowNo,configInfo);
        logger.info("服务时间窗口期中，请求次数达规定次数，切阀值失败率达到预定制：{}----{}",flowNo,configInfo);
        logger.info("服务时间窗口期中，请求次数达规定次数，切阀值失败率达到预定制：{}----{}",flowNo,configInfo);
        return new BasicResponse();
    }

    @RequestMapping(value = "/test/{flowNo}",method = RequestMethod.GET)
    public BasicResponse test(@PathVariable String flowNo){
        logger.info("/EpAlipayBillFlow/flowNo发起请求，请求参数：{}----{}",flowNo,configInfo);
        return epAlipayBillFlowServer.epAlipayBillFlow(flowNo);
    }

    @RequestMapping(value = "/circuitBreaker/{id}",method = RequestMethod.GET)
    public BasicResponse circuitBreaker(@PathVariable Integer id){
        logger.info("/EpAlipayBillFlow/flowNo发起请求，请求参数：{}----{}",id,configInfo);
        return epAlipayBillFlowServer.circuitBreaker(id);
    }
}
