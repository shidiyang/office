package com.lu.office.service.dao.sys;

import com.lu.office.model.sys.UserRolesKey;

public interface UserRolesMapper {
    int deleteByPrimaryKey(UserRolesKey key);

    int insert(UserRolesKey record);

    int insertSelective(UserRolesKey record);
}