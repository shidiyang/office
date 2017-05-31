package com.lu.office.service.dao.people;

import com.lu.office.model.people.Record;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Record record);

    int insertSelective(Record record);

    Record selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Record record);

    int updateByPrimaryKey(Record record);

    int getCountByCont(@Param("cont") String cont);

    List<Record> getListByCont(@Param("offSet") int offSet,@Param("pageSize") int pageSize,@Param("cont") String cont);

    int getCountByContWithName(@Param("cont") String cont);

    List<Record> getListByContWithName(@Param("offSet") int offSet,@Param("pageSize") int pageSize,@Param("cont") StringBuffer cont);
}