package com.wjian.study.hystrix.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wjian.study.domain.basic.BasicResponse;
import com.wjian.study.hystrix.hystrix.HystrixEpAlipayBillFlowServer;
import com.wjian.study.hystrix.hystrix.HystrixWithCauseEpAlipayBillFlowServer;
import io.swagger.models.auth.In;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Generated;


/**
 * @author wangjian
 * @date 2020/6/12 0012 9:46
 */
@Component
@FeignClient(value = "study-nacos-order",fallback = HystrixEpAlipayBillFlowServer.class)
//@FeignClient(value = "study-nacos-order",fallbackFactory = HystrixWithCauseEpAlipayBillFlowServer.class)
public interface EpAlipayBillFlowServer {

    @GetMapping(value = "/EpAlipayBillFlow/{flowNo}")
    BasicResponse epAlipayBillFlow(@PathVariable(value = "flowNo") String flowNo);

    @GetMapping(value = "/circuitBreaker/{id}")
    BasicResponse circuitBreaker(@PathVariable(value = "id") Integer id);
}
