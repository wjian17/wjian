package com.wjian.study.study.nacosorder.service;


import com.wjian.study.domain.rest.EpAlipayBillFlow;

import java.util.List;


public interface EpAlipayBillFlowServer {

    public List<EpAlipayBillFlow> queryEpAlipayBillFlowList(String flowNo);
}
