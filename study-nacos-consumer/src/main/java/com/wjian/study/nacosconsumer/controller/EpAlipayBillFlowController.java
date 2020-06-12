package com.wjian.study.nacosconsumer.controller;

import com.wjian.study.domain.basic.BasicResponse;
import com.wjian.study.nacosconsumer.feign.EpAlipayBillFlowServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
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
    public BasicResponse epAlipayBillFlow(@PathVariable String flowNo){
        logger.info("/EpAlipayBillFlow/flowNo发起请求，请求参数：{}----{}",flowNo,configInfo);
        return epAlipayBillFlowServer.epAlipayBillFlow(flowNo);
    }

    @RequestMapping(value = "/test/{flowNo}",method = RequestMethod.GET)
    public BasicResponse test(@PathVariable String flowNo){
        logger.info("/EpAlipayBillFlow/flowNo发起请求，请求参数：{}----{}",flowNo,configInfo);
        return epAlipayBillFlowServer.epAlipayBillFlow(flowNo);
    }
}
