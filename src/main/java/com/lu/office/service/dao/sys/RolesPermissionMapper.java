package com.lu.office.service.dao.sys;

import com.lu.office.model.sys.RolesPermissionKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolesPermissionMapper {
    int deleteByPrimaryKey(RolesPermissionKey key);

    int insert(RolesPermissionKey record);

    int insertSelective(RolesPermissionKey record);

    List<RolesPermissionKey> getPermissionsByRoleId(@Param("roleId")Integer roleId);

    int getAllCount();

    List<RolesPermissionKey> getPageList(@Param("offSet") int offSet,@Param("pageSize") int pageSize);

    int getAllCountByRoleName(@Param("keyword") String keyword);

    List<RolesPermissionKey> getPageListByRoleName(@Param("offSet") int offSet,@Param("pageSize") int pageSize, @Param("keyword") String keyword);

    RolesPermissionKey getOneByKey(@Param("roleId") Integer roleId,@Param("permissionId") Integer permissionId);

    List<RolesPermissionKey> getListByRolseId(@Param("rolseId") Integer rolseId);
}