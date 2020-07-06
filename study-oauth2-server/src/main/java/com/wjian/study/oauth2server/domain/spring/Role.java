package com.wjian.study.oauth2server.domain.spring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {

	private String id;
	private String name;
	private String desc;
	private Date createTime;
	private Date updateTime;

	private List<Permission> permissions;
}