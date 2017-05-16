package com.lu.office.service.dao.car;

import com.lu.office.model.car.Parking;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ParkingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Parking record);

    int insertSelective(Parking record);

    Parking selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Parking record);

    int updateByPrimaryKey(Parking record);

    int getCountBycont(@Param("cont") String cont);

    List<Parking> getPageListByCont(@Param("offSet") int offSet,@Param("pageSize") int pageSize,@Param("cont") String cont);

    Parking getOneByCont(@Param("cont") String cont);
}