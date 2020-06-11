package com.wjian.study.nacosconsumer.service.impl;

import com.wjian.study.domain.annotation.EnableCuratorZkLock;
import com.wjian.study.domain.rest.EpAlipayBillFlow;
import com.wjian.study.nacosconsumer.mapper.EpAlipayBillFlowMapper;
import com.wjian.study.nacosconsumer.service.EpAlipayBillFlowServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
/**
 * @author wangjian
 * @date 2020/5/28 0028 16:06
 */
 public class EpAlipayBillFlowServerImpl implements EpAlipayBillFlowServer {

    private Logger logger  = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EpAlipayBillFlowMapper epAlipayBillFlowMapper;


    @Autowired(required = false)
    private CacheManager cacheManager;


    @Override
    @EnableCuratorZkLock(value = "#flowNo",time = 120,timeUnit = TimeUnit.SECONDS )
    public List<EpAlipayBillFlow> queryEpAlipayBillFlowList(String flowNo) {
       List<EpAlipayBillFlow> list = epAlipayBillFlowMapper.queryEpAlipayBillFlowList(flowNo);
        return list;
    }
}
