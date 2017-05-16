package com.lu.office.service.repository.sys;

import com.lu.office.model.sys.Page;
import com.lu.office.model.sys.Roles;
import com.lu.office.model.sys.User;

import java.util.List;

/**
 * Created by user on 5/3/17.
 */
public interface UserService {
    User getOneByUserName(User user);

    Page<User> getPageList(int page, int pageSize, String keywork);

    User selectOneByUserName(User user);

    int saveOne(User user);

    int deleteOneById(Integer id);

    List<Roles> getRolesList();
}
