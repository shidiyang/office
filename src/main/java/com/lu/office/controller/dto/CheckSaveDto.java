package com.lu.office.controller.dto;

/**
 * Created by user on 17/1/3.
 */
public class CheckSaveDto<T> {
    private String mes="";
    private T object;
    private int state=0;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
