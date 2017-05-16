package com.lu.office.model.car;

import java.util.Date;

public class Parking {
    private Integer id;

    private Integer parkingId;

    private String adress;

    private Integer type;

    private Integer prince;

    private Integer princeType;

    private Integer status;

    private Integer userId;

    private Date rentTime;

    private String oprator;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParkingId() {
        return parkingId;
    }

    public void setParkingId(Integer parkingId) {
        this.parkingId = parkingId;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress == null ? null : adress.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPrince() {
        return prince;
    }

    public void setPrince(Integer prince) {
        this.prince = prince;
    }

    public Integer getPrinceType() {
        return princeType;
    }

    public void setPrinceType(Integer princeType) {
        this.princeType = princeType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getRentTime() {
        return rentTime;
    }

    public void setRentTime(Date rentTime) {
        this.rentTime = rentTime;
    }

    public String getOprator() {
        return oprator;
    }

    public void setOprator(String oprator) {
        this.oprator = oprator == null ? null : oprator.trim();
    }
}