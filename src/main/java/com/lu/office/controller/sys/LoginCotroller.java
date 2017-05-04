package com.lu.office.controller.sys;

import com.lu.office.service.repository.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;

/**
 * Created by user on 5/4/17.
 */
@Controller
@RequestMapping("login")
public class LoginCotroller {

    @Autowired
    private UserService userService;

    @RequestMapping("check")
    public ModelAndView login(){
        return null;
    }


}
