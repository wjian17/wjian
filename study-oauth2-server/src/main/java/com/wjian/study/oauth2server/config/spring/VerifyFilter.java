package com.wjian.study.oauth2server.config.spring;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @author wangjian
 * @date 2020/6/26 0026 19:40
 */
public class VerifyFilter extends BasicAuthenticationFilter {
    public VerifyFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


}
