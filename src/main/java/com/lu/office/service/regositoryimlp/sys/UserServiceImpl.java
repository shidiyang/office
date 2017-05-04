package com.lu.office.service.regositoryimlp.sys;

import com.lu.office.service.dao.sys.UserMapper;
import com.lu.office.service.repository.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Created by user on 5/3/17.
 *
 *
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
}
