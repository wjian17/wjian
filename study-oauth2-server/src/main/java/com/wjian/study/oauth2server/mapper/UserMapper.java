package com.wjian.study.oauth2server.mapper;

import com.wjian.study.oauth2server.domain.spring.Role;
import com.wjian.study.oauth2server.domain.spring.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

	User loadUserByUsername(@Param("username") String username);

	List<Role> getRolesByUserId(@Param("id") Long id);

	List<Role> getRolesByUserName(@Param("username") String username);

//	CustomerUserDetails loadUserByPassword(@Param("encode") String encode);

	void updatePassword(@Param("username") String username, @Param("newpassword") String newpassword);

	void resetPassword(@Param("username") String username, @Param("resetPassword") String resetPassword);
}