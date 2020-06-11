package com.wjian.study.nacosorder.service;


import com.wjian.study.domain.rest.EpAlipayBillFlow;

import java.util.List;

/**
 * @author wangjian
 * @date 2020/5/28 0028 16:06
 */
public interface EpAlipayBillFlowServer {

    public List<EpAlipayBillFlow> queryEpAlipayBillFlowList(String flowNo);
}
