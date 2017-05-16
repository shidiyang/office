package com.lu.office.service.repositoryimpl.sys;

import com.lu.office.model.sys.Page;
import com.lu.office.model.sys.Roles;
import com.lu.office.model.sys.User;
import com.lu.office.model.sys.UserRolesKey;
import com.lu.office.service.dao.sys.RolesMapper;
import com.lu.office.service.dao.sys.UserMapper;
import com.lu.office.service.dao.sys.UserRolesMapper;
import com.lu.office.service.repository.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 5/3/17.
 *
 *
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RolesMapper rolesMapper;

    @Autowired
    private UserRolesMapper userRolesMapper;

    @Override
    public User getOneByUserName(User user) {
        User user1 = userMapper.getOneByUserName(user.getUserName());
        return user1;
    }

    @Override
    public Page<User> getPageList(int page, int pageSize, String keywork) {
        keywork = keywork.trim();
        StringBuffer cont = new StringBuffer(" and activity = 1");
        if(!"".equals(keywork)){
            cont.append(" and user_name = \'"+keywork+"\'");
        }
        int offSet = (page-1)*pageSize;
        int count = userMapper.getCountByCont(cont.toString());
        List<User> list = new ArrayList<>();
        list = userMapper.getUserPageListByCont(offSet,pageSize,cont.toString());
        Page<User> page1 = new Page<>(list,page,pageSize,count);
        return page1;
    }

    @Override
    public User selectOneByUserName(User user) {
        Integer userId = user.getUserId();
        User user1=null;
        if(userId == null){
            user1 = userMapper.getOneByUserName(user.getUserName());
        }
        user1 = userMapper.getOneByUserNameOutId(user.getUserName(),userId);
        return user1;
    }

    @Override
    public int saveOne(User user) {
        Integer userId = user.getUserId();
        user.setActivity(1);
        int num =0;
        if(userId == null){
            int max = userMapper.getMaxUserId()+1;
            user.setUserId(max);
            num = userMapper.insertSelective(user);
            userRolesMapper.insertOne(max,user.getRoleId());
        }else{
            num = userMapper.updateByPrimaryKeySelective(user);
            userRolesMapper.updateOneByUserId(user.getUserId(),user.getRoleId());
        }
        return num;
    }

    @Override
    public int deleteOneById(Integer id) {
        User user = new User();
        user.setActivity(-2);
        user.setUserId(id);
        int num = userMapper.updateByPrimaryKeySelective(user);
        return num;
    }

    @Override
    public List<Roles> getRolesList() {
        List<Roles> list = new ArrayList<>();
        list = rolesMapper.getAllRoles();
        return list;
    }
}
