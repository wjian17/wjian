package com.wjian.study.order.sentinel.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.wjian.study.domain.basic.BasicResponse;
import com.wjian.study.domain.enums.BasicErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wangjian
 * @date 2020/6/15 0015 21:35
 */
public class GlobeHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public BasicResponse globe_handler_exception(@PathVariable String flowNo, BlockException blockExceptioi){
        logger.info("globeHandler 发起请求，请求参数：{}----{}",flowNo,"handler_exception");
        BasicResponse basicResponse = new BasicResponse();
        basicResponse.setErrorCode(BasicErrorCode.SERVICE_ERROR_CODE);
        basicResponse.setErrorMsg("globeHandler handler_exception");
        return basicResponse;
    }
}
