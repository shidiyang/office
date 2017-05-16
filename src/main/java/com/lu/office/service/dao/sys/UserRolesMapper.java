package com.lu.office.service.dao.sys;

import com.lu.office.model.sys.UserRolesKey;
import org.apache.ibatis.annotations.Param;

public interface UserRolesMapper {
    int deleteByPrimaryKey(UserRolesKey key);

    int insert(UserRolesKey record);

    int insertSelective(UserRolesKey record);

    int insertOne(@Param("max") int max,@Param("roleId") Integer roleId);

    int updateOneByUserId(@Param("userId") Integer userId,@Param("roleId") Integer roleId);
}