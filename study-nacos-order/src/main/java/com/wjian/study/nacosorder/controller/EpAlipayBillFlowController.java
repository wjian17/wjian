package com.wjian.study.nacosorder.controller;

import com.alibaba.fastjson.JSON;
import com.wjian.study.domain.basic.BasicResponse;
import com.wjian.study.domain.enums.BasicErrorCode;
import com.wjian.study.domain.rest.EpAlipayBillFlow;
import com.wjian.study.nacosorder.service.EpAlipayBillFlowServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
/**
 * @author wangjian
 * @date 2020/5/28 0028 16:06
 */
 public class EpAlipayBillFlowController {

    private Logger logger  = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EpAlipayBillFlowServer epAlipayBillFlowServer;

    @RequestMapping(value = "/EpAlipayBillFlow/{flowNo}",method = RequestMethod.GET)
    public BasicResponse epAlipayBillFlow(@PathVariable String flowNo){
        BasicResponse basicResponse = new BasicResponse();
        basicResponse.setErrorCode(BasicErrorCode.SERVICE_SUCCESS_CODE);
        basicResponse.setErrorMsg(BasicErrorCode.SERVICE_SUCCESS_MSG);
        List<EpAlipayBillFlow> epAlipayBillFlowList = epAlipayBillFlowServer.queryEpAlipayBillFlowList(flowNo);
        basicResponse.setBody(epAlipayBillFlowList);
        logger.info("/EpAlipayBillFlow/flowNo发起请求，请求参数：{},请求结果：{}",flowNo, JSON.toJSONString(epAlipayBillFlowList));
        return basicResponse;
    }

    @RequestMapping(value = "/test/{flowNo}",method = RequestMethod.GET)
    public BasicResponse test(@PathVariable String flowNo){
        BasicResponse basicResponse = new BasicResponse();
        basicResponse.setErrorCode(BasicErrorCode.SERVICE_SUCCESS_CODE);
        basicResponse.setErrorMsg(BasicErrorCode.SERVICE_SUCCESS_MSG);
        List<EpAlipayBillFlow> epAlipayBillFlowList = epAlipayBillFlowServer.queryEpAlipayBillFlowList(flowNo);
        basicResponse.setBody(epAlipayBillFlowList);
        logger.info("/test/flowNo发起请求，请求参数：{},请求结果：{}",flowNo, JSON.toJSONString(epAlipayBillFlowList));
        return basicResponse;
    }

    @RequestMapping(value = "/circuitBreaker/{id}",method = RequestMethod.GET)
    public BasicResponse circuitBreaker(@PathVariable Integer id){
        BasicResponse basicResponse = new BasicResponse();
        basicResponse.setErrorCode(BasicErrorCode.SERVICE_SUCCESS_CODE);
        basicResponse.setErrorMsg(BasicErrorCode.SERVICE_SUCCESS_MSG);
        String back = epAlipayBillFlowServer.circuitBreaker(id);
        basicResponse.setBody(back);
        logger.info("/test/flowNo发起请求，请求参数：{},请求结果：{}",id, JSON.toJSONString(back));
        return basicResponse;
    }
}
