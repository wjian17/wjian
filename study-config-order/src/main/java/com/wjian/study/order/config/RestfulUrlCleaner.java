package com.wjian.study.order.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlCleaner;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
public class RestfulUrlCleaner implements UrlCleaner {

    private List<RestfulPattern> patterns = new ArrayList<>();

    private final static Pattern pattern = Pattern.compile("\\{[^\\}]+\\}");

    private RestfulUrlCleaner() {
    }

    /**
     * 根据流量控制规则创建与之匹配的RestfulUrlCleaner
     * @param rules 流量控制规则
     * @return RestfulUrlCleaner
     */
    public static RestfulUrlCleaner create(List<FlowRule> rules) {
        RestfulUrlCleaner cleaner = new RestfulUrlCleaner();
        if (rules == null || rules.size() == 0) {
            return cleaner;
        }
        for (FlowRule rule : rules) {
            Matcher m = pattern.matcher(rule.getResource());
            //如果发现类似{xxx}的结构，断定其为RESTful接口
            if (m.find()) {
                cleaner.patterns.add(
                        new RestfulPattern(Pattern.compile(m.replaceAll("\\\\S+?")), rule.getResource()));
            }
        }
        //根据正则表达式重新排序
        Collections.sort(cleaner.patterns);
        return cleaner;
    }

    @Override
    public String clean(String originUrl) {
        for (RestfulPattern pattern : patterns) {
            if (pattern.getPattern().matcher(originUrl).matches()) {
                return pattern.getRealResource();
            }
        }
        return originUrl;
    }
}