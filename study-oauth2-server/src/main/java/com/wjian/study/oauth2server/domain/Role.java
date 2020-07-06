package com.wjian.study.oauth2server.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class Role implements GrantedAuthority {

    private Long id;

    private String name;

    @Override
    public String getAuthority() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else {
            return obj instanceof Role ? this.name.equals(((Role)obj).name) : false;
        }
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
