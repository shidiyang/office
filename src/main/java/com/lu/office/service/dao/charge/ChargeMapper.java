package com.lu.office.service.dao.charge;

import com.lu.office.model.charge.Charge;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChargeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Charge record);

    int insertSelective(Charge record);

    Charge selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Charge record);

    int updateByPrimaryKey(Charge record);

    int getCountByCont(@Param("cont") String cont);

    List<Charge> getListByCont(@Param("offSet") int offSet,@Param("pageSize") int pageSize,@Param("cont") String cont);

    Charge getOneByCont(@Param("cont") String cont);
}