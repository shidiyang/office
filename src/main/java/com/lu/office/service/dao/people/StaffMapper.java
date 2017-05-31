package com.lu.office.service.dao.people;

import com.lu.office.model.people.Staff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StaffMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Staff record);

    int insertSelective(Staff record);

    Staff selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Staff record);

    int updateByPrimaryKey(Staff record);

    int getCountByCont(@Param("cont") String cont);

    List<Staff> getListByCont(@Param("offSet") int offSet,@Param("pageSize") int pageSize,@Param("cont") String cont);

    Staff getOneByStaffId(@Param("staffId") Integer staffId);

    Staff getOneByCont(@Param("cont") String cont);
}