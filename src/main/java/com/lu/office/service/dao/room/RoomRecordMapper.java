package com.lu.office.service.dao.room;

import com.lu.office.model.room.RoomRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoomRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoomRecord record);

    int insertSelective(RoomRecord record);

    RoomRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoomRecord record);

    int updateByPrimaryKey(RoomRecord record);

    int getCountBycont(@Param("cont") String cont);

    List<RoomRecord> getPageListByCont(@Param("offSet") int offSet,@Param("pageSize") int pageSize,@Param("cont") String cont);

    int getCountWithName(@Param("cont") String cont);

    List<RoomRecord> getPageListWithName(@Param("offSet") int offSet,@Param("pageSize") int pageSize,@Param("cont") String cont);
}