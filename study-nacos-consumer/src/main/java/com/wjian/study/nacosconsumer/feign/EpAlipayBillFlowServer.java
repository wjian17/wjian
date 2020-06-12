package com.wjian.study.nacosconsumer.feign;

import com.wjian.study.domain.basic.BasicResponse;
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
@FeignClient(value = "study-nacos-order")
public interface EpAlipayBillFlowServer {

    @GetMapping(value = "/EpAlipayBillFlow/{flowNo}")
    BasicResponse epAlipayBillFlow(@PathVariable(value = "flowNo") String flowNo);

    @RequestMapping(value = "/test/{flowNo}",method = RequestMethod.GET)
    public BasicResponse test(@PathVariable("flowNo") String flowNo);
}
