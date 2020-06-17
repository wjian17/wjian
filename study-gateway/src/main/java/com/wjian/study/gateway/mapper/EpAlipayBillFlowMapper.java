package com.wjian.study.gateway.mapper;

import com.wjian.study.domain.rest.EpAlipayBillFlow;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author wangjian
 * @date 2020/5/28 0028 16:06
 */
@Mapper
public interface EpAlipayBillFlowMapper {

    int insert(EpAlipayBillFlow record);

    int insertSelective(EpAlipayBillFlow record);

//    @Cacheable(cacheNames = "mybatis")
    List<EpAlipayBillFlow> queryEpAlipayBillFlowList(@Param("flowNo") String flowNo);
}