package com.wjian.study.oauth2server.config.spring;

import com.wjian.study.oauth2server.domain.spring.User;
import com.wjian.study.oauth2server.mapper.UserMapper;
import com.wjian.study.oauth2server.server.impl.OAuth2UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private OAuth2UserDetailsService oAuth2UserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取前端表单中输入后返回的用户名、密码
        String userName = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        User user = (User)oAuth2UserDetailsService.loadUserByUsername(userName);
        boolean isValid = true;
        // 验证密码
        if (!isValid) {
            throw new BadCredentialsException("密码错误！");
        }

        // 前后端分离情况下 处理逻辑...
        // 更新登录令牌 - 之后访问系统其它接口直接通过token认证用户权限...
//        String token = PasswordUtils.encodePassword(System.currentTimeMillis() + user.getSalt(), user.getSalt());
//        User user = userMapper.selectById(user.getId());
//        user.setToken(token);
//        userMapper.updateById(user);
        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}