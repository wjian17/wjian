package com.wjian.study.gateway.hystrix;

import com.wjian.study.gateway.feign.EpAlipayBillFlowServer;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author wangjian
 * @date 2020/6/13 0013 15:19
 */
@Component
public class HystrixWithCauseEpAlipayBillFlowServer implements FallbackFactory<EpAlipayBillFlowServer> {
    @Override
    public EpAlipayBillFlowServer create(Throwable throwable) {
        return null;
    }
}
