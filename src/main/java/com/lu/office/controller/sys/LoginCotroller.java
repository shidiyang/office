package com.lu.office.controller.sys;

import com.lu.office.controller.dto.CheckSaveDto;
import com.lu.office.model.sys.Menu;
import com.lu.office.model.sys.User;
import com.lu.office.service.repository.sys.UserService;
import com.lu.office.service.utile.MailUtil;
import com.lu.office.service.utile.WebUtile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.zip.Checksum;

/**
 * Created by user on 5/4/17.
 */
@Controller
@RequestMapping("/login")
public class LoginCotroller {
    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request,
                             HttpServletResponse response){
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @RequestMapping("/check")
    public @ResponseBody CheckSaveDto<User> check(HttpServletRequest request,
                                                  HttpServletResponse response,
                                                  @RequestParam("code")String code1,
                                                  User user){
        CheckSaveDto<User> dto = new CheckSaveDto<>();
        String code = (String) request.getSession().getAttribute("code");
        if(!code1.equalsIgnoreCase(code)){
            dto.setState(1);
            dto.setMes("验证码错误。");
            return dto;
        }
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

                HttpSession session = request.getSession();
                session.setAttribute("user",user1);
                List<Menu> menus = userService.getMenus(user1);
                session.setAttribute("menus",menus);
                return dto;
            }
        }
    }

    @RequestMapping("/check_pass")
    public @ResponseBody CheckSaveDto<User> check_pass(HttpServletRequest request,
                                                  HttpServletResponse response,
                                                  @RequestParam("find_code")String code1,
                                                  @RequestParam("mail_name")String mailName){
        CheckSaveDto<User> dto = new CheckSaveDto<>();
        String code = (String) request.getSession().getAttribute("code");
        if(!code1.equalsIgnoreCase(code)){
            dto.setState(1);
            dto.setMes("验证码错误。");
            return dto;
        }
        if(!WebUtile.checkEmail(mailName)){
            dto.setState(1);
            dto.setMes("邮箱格式错误。");
            return dto;
        }
        User user= userService.getOneUserByEmail(mailName);
        if(user == null){
            dto.setState(1);
            dto.setMes("此邮箱未被注册过.");
            return dto;
        }else{
            dto.setState(0);
            dto.setMes("邮件验证成功,请发送.");
            return dto;
        }
    }

    @RequestMapping("/out")
    public ModelAndView logout(HttpServletRequest request,
                       HttpServletResponse response){
        ModelAndView mv = new ModelAndView("login");
        request.getSession().invalidate();
        Cookie   userName = new Cookie("mycookie",null);
        userName.setMaxAge(0);
        userName.setPath("/");
        response.addCookie(userName);
        return mv;
    }

    @RequestMapping("/sent")
    public @ResponseBody boolean setEmail(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestParam("mail_name")String mailName){
        MailUtil mu=new MailUtil();
        User user= userService.getOneUserByEmail(mailName);
        String toEmails = mailName;
        String subject = "密码找回";
        StringBuilder builder = new StringBuilder();
        builder.append("<html><body>"+user.getUserName()+"：<br />您的密码是:"+user.getPassword()+"<br /></body></html>");
        String content = builder.toString();

        mu.setToEmails(toEmails);
        mu.setSubject(subject);
        mu.setContent(content);
        String mes;
        try {
            mu.sendEmail();
        } catch (Exception e) {
            e.printStackTrace();
            mes = e.getMessage();
            System.out.println(mes);
            return false;
        }
        return true;
    }


}
