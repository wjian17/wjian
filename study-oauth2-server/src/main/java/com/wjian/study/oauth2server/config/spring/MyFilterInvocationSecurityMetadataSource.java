package com.wjian.study.oauth2server.config.spring;

import cn.hutool.core.util.StrUtil;
import com.wjian.study.domain.enums.StatusCode;
import com.wjian.study.oauth2server.domain.spring.Permission;
import com.wjian.study.oauth2server.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.parameters.P;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.*;

@Component
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    
    @Autowired
    private PermissionMapper permissionMapper;
    
    private Map<String, Collection<ConfigAttribute>> map = null;
    /**
     * 加载权限表中所有操作请求权限
     */
    public void loadResourceDefine() {
        map = new HashMap<>(16);
        Collection<ConfigAttribute> configAttributes;
        ConfigAttribute cfg;
        // 获取启用的权限操作请求
//        List<Permission> permissions = permissionMapper.findPermissionsAll();
        List<Permission> permissions = new ArrayList<>();
        Permission permission1 = new Permission();
        permission1.setId(1);
        permission1.setMenuId("1");
        permission1.setGrantAuthority("URL_P1");
        permissions.add(permission1);
        for (Permission permission : permissions) {
            if (StrUtil.isNotBlank(permission.getGrantAuthority()) && StrUtil.isNotBlank(permission.getGrantAuthority())) {
                configAttributes = new ArrayList<>();
                cfg = new SecurityConfig(permission.getGrantAuthority());
                //作为MyUrlAccessDecisionManager类的decide的第三个参数
                configAttributes.add(cfg);
                //用权限的path作为map的key，用ConfigAttribute的集合作为value
                map.put(permission.getMenuId(), configAttributes);
            }
        }
    }
    /**
     * 判定用户请求的url是否在权限表中
     * 如果在权限表中，则返回给decide方法，用来判定用户是否有此权限
     * 如果不在权限表中则放行
     *
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (map == null) {
            loadResourceDefine();
        }
        //Object中包含用户请求request
        String url = ((FilterInvocation) object).getRequestUrl();
        PathMatcher pathMatcher = new AntPathMatcher();
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String resURL = iterator.next();
            if (StrUtil.isNotBlank(resURL) && pathMatcher.match(resURL, url)) {
                return map.get(resURL);
            }
        }
        return null;
    }
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}