package com.lu.office.service.dao.sys;

import com.lu.office.model.sys.Roles;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolesMapper {
    int deleteByPrimaryKey(@Param("roleId") Integer roleId);

    int insert(Roles record);

    int insertSelective(Roles record);

    Roles selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Roles record);

    int updateByPrimaryKey(Roles record);

    List<Roles> getAllRoles();

    Roles getRoleByName(@Param("name") String name);
}