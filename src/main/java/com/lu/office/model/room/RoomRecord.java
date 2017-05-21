package com.lu.office.model.room;

import com.lu.office.model.sys.User;

import java.util.Date;

public class RoomRecord {
    private Integer id;

    private String operator;

    private Integer rentorId;

    private Date operateTime;

    private Integer opertateType;

    private Integer roomId;

    private String roomName;

    private User rentor;

    public User getRentor() {
        return rentor;
    }

    public void setRentor(User rentor) {
        this.rentor = rentor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Integer getRentorId() {
        return rentorId;
    }

    public void setRentorId(Integer rentorId) {
        this.rentorId = rentorId;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public Integer getOpertateType() {
        return opertateType;
    }

    public void setOpertateType(Integer opertateType) {
        this.opertateType = opertateType;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName == null ? null : roomName.trim();
    }
}