package com.lu.office.service.dao.sys;

import com.lu.office.model.sys.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(@Param("userId") Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User getOneByUserName(@Param("userName") String userName);

    int getCountByCont(@Param("cont") String cont);

    List<User> getUserPageListByCont(@Param("offSet") int offSet,@Param("pageSize") int pageSize,@Param("cont") String cont);

    User getOneByUserNameOutId(@Param("userName") String userName,@Param("userId") Integer userId);

    int getMaxUserId();
}