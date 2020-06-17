package com.wjian.study.nacosorder.service.impl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wjian.study.domain.annotation.EnableCuratorZkLock;
import com.wjian.study.domain.rest.EpAlipayBillFlow;
import com.wjian.study.nacosorder.mapper.EpAlipayBillFlowMapper;
import com.wjian.study.nacosorder.service.EpAlipayBillFlowServer;
import org.apache.tomcat.jni.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
/**
 * @author wangjian
 * @date 2020/5/28 0028 16:06
 */
public class EpAlipayBillFlowServerImpl implements EpAlipayBillFlowServer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EpAlipayBillFlowMapper epAlipayBillFlowMapper;


    @Autowired(required = false)
    private CacheManager cacheManager;

    @Value("${server.port}")
    private String port;

    @Override
    @HystrixCommand(fallbackMethod = "queryEpAlipayBillFlowList_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public List<EpAlipayBillFlow> queryEpAlipayBillFlowList(String flowNo) {

        try {
            Thread.sleep(Long.parseLong(flowNo));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<EpAlipayBillFlow> list = epAlipayBillFlowMapper.queryEpAlipayBillFlowList(flowNo);
        return list;
    }


    public List<EpAlipayBillFlow> queryEpAlipayBillFlowList_TimeOutHandler(String flowNo) {
        logger.info("线程池:  " + Thread.currentThread().getName() + port + "系统繁忙或者运行报错，请稍后再试,flowNo:  " + flowNo + "\t" + "o(╥﹏╥)o");
        return new ArrayList<>();
    }

    //=====服务熔断
    @Override
    @HystrixCommand(fallbackMethod = "circuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),// 失败率达到多少后跳闸
    })
    public String circuitBreaker(@PathVariable("id") Integer id) {
        try {
            Thread.sleep(Math.abs(id)*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (id < 0) {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + "\t" + "调用成功，流水号: " + serialNumber;
    }

    public String circuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " + id;
    }
}
