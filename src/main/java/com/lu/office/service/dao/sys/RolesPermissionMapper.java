package com.lu.office.service.dao.sys;

import com.lu.office.model.sys.RolesPermissionKey;

public interface RolesPermissionMapper {
    int deleteByPrimaryKey(RolesPermissionKey key);

    int insert(RolesPermissionKey record);

    int insertSelective(RolesPermissionKey record);
}