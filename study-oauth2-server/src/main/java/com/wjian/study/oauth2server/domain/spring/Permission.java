package com.wjian.study.oauth2server.domain.spring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements GrantedAuthority,Serializable {

	private Integer id;
	private Integer parentId;
	private String name;
	private String icon;
	private String menuId;
	private String createTime;
	private String updateTime;
	private String desc;
	//权限
	private String grantAuthority;

	@Override
	public String getAuthority() {
		return grantAuthority;
	}
}