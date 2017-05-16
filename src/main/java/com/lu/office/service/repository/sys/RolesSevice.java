package com.lu.office.service.repository.sys;

import com.lu.office.model.sys.Page;
import com.lu.office.model.sys.Permission;
import com.lu.office.model.sys.Roles;
import com.lu.office.model.sys.RolesPermissionKey;

import java.util.List;

/**
 * Created by user on 5/12/17.
 */
public interface RolesSevice {
    Page<RolesPermissionKey> getPageList(int page, int pageSize, String keyword);

    List<Permission> getPermissionList();

    Roles getRoleByName(String description);

    Integer insertOne(String description);

    RolesPermissionKey getOneByKey(RolesPermissionKey rolesPermissionKey);

    int saveOneRolesPermissionKey(RolesPermissionKey rolesPermissionKey);

    int deleteOneByKey(Integer roleId, Integer permissId);
}
