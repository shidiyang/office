package com.lu.office.service.repository.sys;

import com.lu.office.model.sys.User;

/**
 * Created by user on 5/3/17.
 */
public interface UserService {
    User getOneByUserName(User user);
}
