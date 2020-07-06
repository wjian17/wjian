package com.wjian.study.oauth2server.mapper;

import com.wjian.study.oauth2server.domain.spring.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
	List<Role> roles();
	List<Role> getRolesByUrl(String url);
	public int addRole(@Param("roles") Role role);
	public int updateRoleByRoleId(@Param("roleId") long roleId, @Param("roles") Role role);
	public int delRoleByRoleId(@Param("roleId") long roleId);
	public List<Role> findRolesAll();
	public List<Role> findRoles();
	public int addRelations(@Param("rolePermRelation") List<String> rolePermRelationList, @Param("roleId") long roleId);
	public int delRelations(@Param("roleId") long roleId, @Param("deletePerID") long deletePerID);

}