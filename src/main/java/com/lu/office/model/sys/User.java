package com.lu.office.model.sys;

public class User {
    private Integer userId;

    private String userName;

    private String password;

    private Integer sex;

    private String phone;

    private String emall;

    private Integer activity;

    private UserRolesKey userRolesKey;

    public UserRolesKey getUserRolesKey() {
        return userRolesKey;
    }

    public void setUserRolesKey(UserRolesKey userRolesKey) {
        this.userRolesKey = userRolesKey;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmall() {
        return emall;
    }

    public void setEmall(String emall) {
        this.emall = emall == null ? null : emall.trim();
    }

    public Integer getActivity() {
        return activity;
    }

    public void setActivity(Integer activity) {
        this.activity = activity;
    }
}