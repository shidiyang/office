package com.lu.office.model.car;

import com.lu.office.model.sys.User;

import java.util.Date;

public class ParkingRecord {
    private Integer id;

    private String oprerator;

    private Integer rentorId;

    private Date operateTime;

    private Integer operateType;

    private Integer account;

    private Integer parkingId;

    private String parkingAdress;

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

    public String getOprerator() {
        return oprerator;
    }

    public void setOprerator(String oprerator) {
        this.oprerator = oprerator == null ? null : oprerator.trim();
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

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public Integer getParkingId() {
        return parkingId;
    }

    public void setParkingId(Integer parkingId) {
        this.parkingId = parkingId;
    }

    public String getParkingAdress() {
        return parkingAdress;
    }

    public void setParkingAdress(String parkingAdress) {
        this.parkingAdress = parkingAdress == null ? null : parkingAdress.trim();
    }
}