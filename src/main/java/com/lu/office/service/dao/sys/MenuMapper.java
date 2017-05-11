package com.lu.office.service.dao.sys;

import com.lu.office.model.sys.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> getAll();

    int getAllCount();

    List<Menu> getPage(@Param("offSet") int offSet,@Param("pageSize") int pageSize);

    int getAllCountByParentId(@Param("parentId")Integer parentId);

    List<Menu> getPageByParentId(@Param("offSet") int offSet,@Param("pageSize") int pageSize,@Param("parentId") Integer parentId);

    Menu getOneByName(@Param("text") String text);

    Menu getOneByCont(@Param("cont") String cont);
}