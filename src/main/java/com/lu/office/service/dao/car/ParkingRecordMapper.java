package com.lu.office.service.dao.car;

import com.lu.office.model.car.ParkingRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ParkingRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParkingRecord record);

    int insertSelective(ParkingRecord record);

    ParkingRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ParkingRecord record);

    int updateByPrimaryKey(ParkingRecord record);

    int getCountBycont(@Param("cont") String cont);

    List<ParkingRecord> getPageListByCont(@Param("offSet") int offSet,@Param("pageSize") int pageSize,@Param("cont") String cont);

    int getCountWithName(@Param("cont") String cont);

    List<ParkingRecord> getPageListWithName(@Param("offSet") int offSet,@Param("pageSize") int pageSize,@Param("cont") String cont);
}