package com.wjian.study.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.wjian.study.domain.enums.BasicErrorCode;
import com.wjian.study.order.feign.EpAlipayBillFlowServer;
import com.wjian.study.domain.basic.BasicResponse;
import com.wjian.study.order.sentinel.handler.GlobeHandler;
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
//    @SentinelResource(value ="test",blockHandler = "handler_exception")
    @SentinelResource(value ="test",blockHandler = "globe_handler_exception",blockHandlerClass = GlobeHandler.class)
    public BasicResponse test(@PathVariable String flowNo){
        logger.info("/test/flowNo发起请求，请求参数：{}----{}",flowNo,configInfo);
        return epAlipayBillFlowServer.epAlipayBillFlow(flowNo);
    }

    public BasicResponse handler_exception(@PathVariable String flowNo,BlockException blockExceptioi){
        logger.info("/test/flowNo发起请求，请求参数：{}----{}",flowNo,"handler_exception");
        BasicResponse basicResponse = new BasicResponse();
        basicResponse.setErrorCode(BasicErrorCode.SERVICE_ERROR_CODE);
        basicResponse.setErrorMsg("handler_exception");
        return basicResponse;

    }
    @RequestMapping(value = "/circuitBreaker/{id}",method = RequestMethod.GET)
    public BasicResponse circuitBreaker(@PathVariable Integer id){
        logger.info("/EpAlipayBillFlow/flowNo发起请求，请求参数：{}----{}",id,configInfo);
        return epAlipayBillFlowServer.circuitBreaker(id);
    }
}
