package com.lu.office.service.dao.room;

import com.lu.office.model.car.Parking;
import com.lu.office.model.room.Room;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoomMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Room record);

    int insertSelective(Room record);

    Room selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Room record);

    int updateByPrimaryKey(Room record);

    int getCountBycont(@Param("cont") String cont);

    List<Room> getPageListByCont(@Param("offSet") int offSet,@Param("pageSize") int pageSize,@Param("cont") String cont);

    Room getOneByCont(@Param("cont") String cont);

    int getCountBycontWithName(@Param("cont") String cont);

    List<Room> getPageListByContWithName(@Param("offSet") int offSet,@Param("pageSize") int pageSize,@Param("cont") String cont);
}