package com.lu.office.model.sys;

import java.util.List;

public class Roles {
    private Integer roleId;

    private String description;

    private List<RolesPermissionKey> rolesPermissionKeys;

    public List<RolesPermissionKey> getRolesPermissionKeys() {
        return rolesPermissionKeys;
    }

    public void setRolesPermissionKeys(List<RolesPermissionKey> rolesPermissionKeys) {
        this.rolesPermissionKeys = rolesPermissionKeys;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}