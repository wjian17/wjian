package com.wjian.study.order.config;

import java.util.regex.Pattern;

public class RestfulPattern implements Comparable<RestfulPattern> {
    private Pattern pattern;
    private String realResource;

    public RestfulPattern(Pattern pattern, String realResource) {
        this.pattern = pattern;
        this.realResource = realResource;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public String getRealResource() {
        return realResource;
    }

    @Override
    public int compareTo(RestfulPattern o) {
        return o.getPattern().pattern().compareTo(this.getPattern().pattern());
    }
}