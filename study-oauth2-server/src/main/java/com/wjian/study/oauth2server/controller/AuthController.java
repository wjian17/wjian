package com.wjian.study.oauth2server.controller;

import com.wjian.study.domain.basic.BasicResponse;
import com.wjian.study.domain.enums.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangjian
 * @date 2020/6/25 0025 17:15
 */
@RestController
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "getAll",method = RequestMethod.GET)
    public BasicResponse getAll(){
        logger.info("getAll is running ");
        logger.info("getAll is running ");
        logger.info("getAll is running ");
        BasicResponse basicResponse = StatusCode.getBasicResponse(StatusCode.SUCCESS);
        basicResponse.setBody("getAll is running!");
        return basicResponse;
    }

    @RequestMapping(value = "postAll",method = RequestMethod.POST)
    public BasicResponse postAll(){
        logger.info("postAll is running ");
        logger.info("postAll is running ");
        logger.info("postAll is running ");
        BasicResponse basicResponse = StatusCode.getBasicResponse(StatusCode.SUCCESS);
        basicResponse.setBody("postAll is running!");
        return basicResponse;
    }
}
