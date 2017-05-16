package com.lu.office.service.repositoryimpl.sys;

import com.lu.office.model.sys.Page;
import com.lu.office.model.sys.Permission;
import com.lu.office.model.sys.Roles;
import com.lu.office.model.sys.RolesPermissionKey;
import com.lu.office.service.dao.sys.PermissionMapper;
import com.lu.office.service.dao.sys.RolesMapper;
import com.lu.office.service.dao.sys.RolesPermissionMapper;
import com.lu.office.service.repository.sys.RolesSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 5/12/17.
 */
@Repository("rolesService")
public class RolesServiceImpl  implements RolesSevice{

    @Autowired
    private RolesMapper rolesMapper;

    @Autowired
    private RolesPermissionMapper rolesPermissionMapper;

    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public Page<RolesPermissionKey> getPageList(int page, int pageSize, String keyword) {
        keyword = keyword.trim();
        int offSet = (page-1)*pageSize;
        int count = 0;
        List<RolesPermissionKey> list = new ArrayList<>();
        if("".equals(keyword)){
            count = rolesPermissionMapper.getAllCount();
            list = rolesPermissionMapper.getPageList(offSet,pageSize);
        }else{
            count = rolesPermissionMapper.getAllCountByRoleName(keyword);
            list = rolesPermissionMapper.getPageListByRoleName(offSet,pageSize,keyword);
        }
        Page<RolesPermissionKey> page1 = new Page<>(list,page,pageSize,count);
        return page1;
    }

    @Override
    public List<Permission> getPermissionList() {
        List<Permission> list = new ArrayList<>();
        list = permissionMapper.getAllList();
        return list;
    }

    @Override
    public Roles getRoleByName(String description) {
        Roles roles = rolesMapper.getRoleByName(description);
        return roles;
    }

    @Override
    public Integer insertOne(String description) {
        Roles roles = new Roles();
        roles.setDescription(description);
        Integer num = rolesMapper.insertSelective(roles);
        return num;
    }

    @Override
    public RolesPermissionKey getOneByKey(RolesPermissionKey rolesPermissionKey) {
        Roles roles = rolesMapper.getRoleByName(rolesPermissionKey.getRoles().getDescription());
        RolesPermissionKey rolesPermissionKey1 = rolesPermissionMapper.getOneByKey(roles.getRoleId(),rolesPermissionKey.getPermissionId());
        return rolesPermissionKey1;
    }

    @Override
    public int saveOneRolesPermissionKey(RolesPermissionKey rolesPermissionKey) {
        Roles roles = rolesMapper.getRoleByName(rolesPermissionKey.getRoles().getDescription());
        rolesPermissionKey.setRoleId(roles.getRoleId());
        int  num = rolesPermissionMapper.insertSelective(rolesPermissionKey);
        return num;
    }

    @Override
    public int deleteOneByKey(Integer roleId, Integer permissId) {
        RolesPermissionKey rolesPermissionKey = new RolesPermissionKey();
        rolesPermissionKey.setRoleId(roleId);
        rolesPermissionKey.setPermissionId(permissId);
        int num = rolesPermissionMapper.deleteByPrimaryKey(rolesPermissionKey);
        return num;
    }
}
