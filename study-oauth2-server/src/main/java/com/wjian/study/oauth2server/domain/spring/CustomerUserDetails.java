package com.wjian.study.oauth2server.domain.spring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * @author wangjian
 * @date 2020/6/22 0022 14:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUserDetails implements UserDetails {

	@NotNull
    private User user;

    private Collection<? extends GrantedAuthority> authorities;


    @Override
    public boolean equals(Object customerUserDetails) {
        return customerUserDetails instanceof CustomerUserDetails
                && this.getUsername().equals(((CustomerUserDetails) customerUserDetails).getUsername());
    }

    @Override
    public int hashCode() {
        return this.getUsername().hashCode();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
		return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
		return true;
    }

    @Override
    public boolean isEnabled() {
		return true;
    }
}