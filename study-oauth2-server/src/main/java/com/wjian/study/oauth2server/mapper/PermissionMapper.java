package com.wjian.study.oauth2server.mapper;

import com.wjian.study.oauth2server.domain.spring.Permission;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface PermissionMapper {

	List<Permission> getAllPermission();

	List<Permission> findPermissionsAll();

	int addPermissions(@Param("permissions") Permission permission);

	int updatePermissionsById(@Param("permissionsId") long permissionsId, @Param("permissions") Permission permission);

	int delPermissionsById(@Param("permissionsId") long permissionsId);

	List<Permission> findPermissionsAll2();

	List<Permission> findPermissionsAll3();

	List<Permission> findPermissionsAllByparentId1();

	List<Permission> findPermissionsAllByparentId2();
}