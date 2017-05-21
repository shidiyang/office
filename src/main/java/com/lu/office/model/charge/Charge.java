package com.lu.office.model.charge;

public class Charge {
    private Integer id;

    private String chargeName;

    private Integer chargeType;

    private Integer prince;

    private String unit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChargeName() {
        return chargeName;
    }

    public void setChargeName(String chargeName) {
        this.chargeName = chargeName == null ? null : chargeName.trim();
    }

    public Integer getChargeType() {
        return chargeType;
    }

    public void setChargeType(Integer chargeType) {
        this.chargeType = chargeType;
    }

    public Integer getPrince() {
        return prince;
    }

    public void setPrince(Integer prince) {
        this.prince = prince;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }
}