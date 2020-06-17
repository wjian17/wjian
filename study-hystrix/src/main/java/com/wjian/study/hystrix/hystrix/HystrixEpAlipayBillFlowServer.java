package com.wjian.study.hystrix.hystrix;

import com.wjian.study.domain.basic.BasicResponse;
import com.wjian.study.hystrix.feign.EpAlipayBillFlowServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @author wangjian
 * @date 2020/6/12 0012 9:46
 */
@Component
public class HystrixEpAlipayBillFlowServer implements EpAlipayBillFlowServer {

    private Logger logger  = LoggerFactory.getLogger(this.getClass());

    @Override
    public BasicResponse epAlipayBillFlow(String flowNo) {
        logger.info("服务降级：{}",flowNo);
        logger.info("服务降级：{}",flowNo);
        logger.info("服务降级：{}",flowNo);
        return new BasicResponse();
    }

    @Override
    public BasicResponse circuitBreaker(Integer id) {
        logger.info("服务降级：{}",id);
        logger.info("服务降级：{}",id);
        logger.info("服务降级：{}",id);
        return new BasicResponse();
    }
}
