package com.lu.office.service.dao.sys;

import com.lu.office.model.sys.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer permissionId);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(@Param("permissionId") Integer permissionId);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    Permission getOneByName(@Param("permissionName") String permissionName);

    Integer getMaxId();

    List<Permission> getAllList();
}