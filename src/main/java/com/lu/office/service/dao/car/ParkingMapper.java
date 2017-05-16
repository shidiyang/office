package com.lu.office.service.dao.car;

import com.lu.office.model.car.Parking;

public interface ParkingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Parking record);

    int insertSelective(Parking record);

    Parking selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Parking record);

    int updateByPrimaryKey(Parking record);
}