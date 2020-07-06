package com.wjian.study.oauth2server.domain.spring;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.io.Serializable;
import java.util.*;

@Data
public class User implements UserDetails,Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	/**
	 *由用户名+手机号+邮箱生成的登录账号
	 */
	private String uuid;
	private String username;
	private String phone;
	private String email;
	private String password;
	/**
	 * 	盐(前端不需要管)
 	 */
	private String salt;
	/**
	 * 是否锁定（0表示未锁定，可用，1表示不可用）
	 */
	private int locked = 0;
	/**
	 * 公司id
	 */
	private long companyId;
	private boolean rememberMe;
	private Date createTime;
	private Date modifyTime;
	private final boolean accountNonExpired;
	private final boolean accountNonLocked;
	private final boolean credentialsNonExpired;
	private final boolean enabled;
	private List<Role> roles = new ArrayList<>();

	private Set<Permission> permissions;

	public User(String username, String password, List<Role> roles) {
		this(username, password, roles, true, true, true ,true);
	}

	public User(String username, String password, List<Role> roles, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled) {
		this.username = username;
		this.password = password;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		for (Role role:this.roles) {
			List<Permission> permissions = role.getPermissions();
			for(Permission permission:permissions){
				this.permissions.add(permission);
			}
		}
		return permissions;
	}
}