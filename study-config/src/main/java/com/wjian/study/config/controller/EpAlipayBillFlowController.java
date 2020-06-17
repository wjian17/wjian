package com.wjian.study.config.controller;

import com.wjian.study.config.feign.EpAlipayBillFlowServer;
import com.wjian.study.config.stream.MessageProvider;
import com.wjian.study.domain.basic.BasicResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @Resource
    private MessageProvider messageProvider;

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
        messageProvider.send();
        return epAlipayBillFlowServer.epAlipayBillFlow(flowNo);
    }

    @RequestMapping(value = "/circuitBreaker/{id}",method = RequestMethod.GET)
    public BasicResponse circuitBreaker(@PathVariable Integer id){
        logger.info("/EpAlipayBillFlow/flowNo发起请求，请求参数：{}----{}",id,configInfo);
        return epAlipayBillFlowServer.circuitBreaker(id);
    }
}
