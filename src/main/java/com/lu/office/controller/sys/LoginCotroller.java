package com.lu.office.controller.sys;

import com.lu.office.controller.dto.CheckSaveDto;
import com.lu.office.model.sys.User;
import com.lu.office.service.repository.sys.UserService;
import com.lu.office.service.utile.WebUtile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by user on 5/4/17.
 */
@Controller
@RequestMapping("/login")
public class LoginCotroller {
    @Autowired
    private UserService userService;

    @RequestMapping("/check")
    public @ResponseBody CheckSaveDto<User> check(HttpServletRequest request,
                                                  HttpServletResponse response,
                                                  User user){
        CheckSaveDto<User> dto = new CheckSaveDto<>();
        User user1 = userService.getOneByUserName(user);
        if(user1 == null){
            dto.setState(1);
            dto.setMes("用户名不存在。");
            return dto;
        }else{
            if(!user.getPassword().equals(user1.getPassword()) ){
                dto.setState(1);
                dto.setMes("密码错误。");
                return dto;
            }else{
                dto.setState(0);
                dto.setMes("登陆成功。");
                //写入cookie
                Cookie cookie = new Cookie("userName", WebUtile.encodeBase64(user.getUserName()));
                cookie.setMaxAge( 24 * 60 * 60);// 设置为1天
                cookie.setPath("/");
                response.addCookie(cookie);
                return dto;
            }
        }
    }


}
