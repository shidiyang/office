package com.lu.office.service.dao.people;

import com.lu.office.model.people.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    int getCountBycont(@Param("cont") String cont);

    List<Department> getPageListByCont(@Param("offSet") int offSet,@Param("pageSize") int pageSize,@Param("cont") String cont);

    Department getOneByCont(@Param("cont") String cont);
}