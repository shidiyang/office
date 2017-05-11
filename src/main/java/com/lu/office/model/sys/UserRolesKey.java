package com.lu.office.model.sys;

public class UserRolesKey {
    private Integer userId;

    private Integer roleId;

    private Roles role;

    public Roles getRole() {
        return role;
    }

    public void setRoles(Roles roles) {
        this.role = roles;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}