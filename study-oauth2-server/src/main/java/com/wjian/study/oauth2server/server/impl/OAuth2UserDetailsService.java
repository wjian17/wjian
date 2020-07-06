package com.wjian.study.oauth2server.server.impl;

import com.wjian.study.oauth2server.domain.spring.Role;
import com.wjian.study.oauth2server.domain.spring.User;
import com.wjian.study.oauth2server.mapper.UserMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OAuth2UserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMapper userMapper;

    /**
     * SecurityContextHolder
     * 用户在完成登录后 Security 会将用户信息存储到这个类中，之后其他流程需要得到用户信息时都是从这个类中获得，
     * 用户信息被封装成 SecurityContext ，而实际存储的类是 SecurityContextHolderStrategy ，默认的SecurityContextHolderStrategy
     * 实现类是 ThreadLocalSecurityContextHolderStrategy 它使用了ThreadLocal来存储了用户信息。
     * sernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("test","test",list);
     * SecurityContextHolder.getContext().setAuthentication(token);
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("表单登录用户名:" + username);
        if(StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("UserDetailsService没有接收到用户账号");
        } else {
            logger.info("密码：==｛｝",new BCryptPasswordEncoder().encode("admin"));
            logger.info("密码：==｛｝"+new BCryptPasswordEncoder().encode("admin"));
            User user = new User("admin",new BCryptPasswordEncoder().encode("admin"),null);
            /**
             * 创建一个用于认证的用户对象并返回，包括：用户名，密码，角色
             */
            return user;
//            /**
//             * 根据用户名查找用户信息
//             */
//            User user = userMapper.loadUserByUsername(username);
//            if(user == null) {
//                throw new UsernameNotFoundException(String.format("用户'%s'不存在", username));
//            }
//            List<Role> roles = new ArrayList<>();
//            for (Role role : user.getRoles()) {
//                //封装用户信息和角色信息到SecurityContextHolder全局缓存中
//                roles.add(role);
//            }
//            /**
//             * 创建一个用于认证的用户对象并返回，包括：用户名，密码，角色
//             */
//            return user;
        }
    }

}