package com.lu.office.controller.sys;

import com.lu.office.controller.dto.CheckSaveDto;
import com.lu.office.model.sys.Page;
import com.lu.office.model.sys.Roles;
import com.lu.office.model.sys.User;
import com.lu.office.service.repository.sys.UserService;
import com.lu.office.service.utile.WebUtile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by user on 5/12/17.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public ModelAndView getList(HttpServletRequest request,
                                HttpServletResponse response,
                                @RequestParam(value = "keyword",defaultValue = "")String keywork,
                                @RequestParam(value = "page", defaultValue = "1") int page,
                                @RequestParam(value = "pageSize", defaultValue = "15") int pageSize){
        ModelAndView mv = new ModelAndView("/sys/user");
        Page<User> page1 = userService.getPageList(page,pageSize,keywork);
        List<Roles> roles = userService.getRolesList();
        mv.addObject("roles",roles);
        mv.addObject("page",page1);
        mv.addObject("keyword",keywork);
        return mv;
    }

    @RequestMapping("/check")
    public @ResponseBody CheckSaveDto<User> check(HttpServletResponse response,
                                                  HttpServletRequest request,
                                                  User user){
        CheckSaveDto<User> checkSaveDto = new CheckSaveDto<>();
        User user1 = userService.selectOneByUserName(user);
        if(user1 != null){
            checkSaveDto.setState(1);
            checkSaveDto.setMes("用户名: "+user.getUserName()+"已经存在,请修改后添加.");
            return checkSaveDto;
        }
        if(!WebUtile.checkEmail(user.getEmall())){
            checkSaveDto.setState(1);
            checkSaveDto.setMes("邮箱格式存在问题,请修改后添加.");
            return checkSaveDto;
        }
        if(!WebUtile.checkMobileNumber(user.getPhone())){
            checkSaveDto.setState(1);
            checkSaveDto.setMes("电话格式存在问题,请修改后添加.");
            return checkSaveDto;
        }
        return checkSaveDto;
    }

    @RequestMapping("/save")
    public @ResponseBody boolean save(HttpServletRequest request,
                                      HttpServletResponse response,
                                      User user){
        int num = userService.saveOne(user);
        if(num > 0){
            return true;
        }
        return false;
    }

    @RequestMapping("/del")
    public @ResponseBody CheckSaveDto<User> delete(HttpServletResponse response,
                                                   HttpServletRequest request,
                                                   @RequestParam("id")Integer id){
        CheckSaveDto<User> checkSaveDto = new CheckSaveDto<>();
        int num =userService.deleteOneById(id);
        if(num >0){
            checkSaveDto.setState(0);
            checkSaveDto.setMes("删除成功.");
            return checkSaveDto;
        }else{
            checkSaveDto.setState(1);
            checkSaveDto.setMes("删除失败.");
            return checkSaveDto;
        }
    }

    @RequestMapping("/info")
    public ModelAndView getInfo(HttpServletRequest request,
                                HttpServletResponse response){
        ModelAndView mv = new ModelAndView("/sys/info");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        List<Roles> roles = userService.getRolesList();
        mv.addObject("roles",roles);
        mv.addObject("userInfo",user);
        return mv;
    }

    @RequestMapping("/alterps")
    public ModelAndView alterps(HttpServletRequest request,
                                HttpServletResponse response){
        ModelAndView mv = new ModelAndView("/sys/alterps");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        mv.addObject("userInfo",user);
        return mv;
    }

    @RequestMapping("/saveps")
    public @ResponseBody boolean saveps(HttpServletRequest request,
                                        HttpServletResponse response,
                                        @RequestParam("userId")Integer userId,
                                        @RequestParam("password")String password){
        User user =userService.getOneByUserId(userId);
        user.setPassword(password);
        user.setRoleId(user.getUserRolesKey().getRoleId());
        int num = userService.saveOne(user);
        if(num > 0){
            return true;
        }
        return false;
    }

    @RequestMapping("/checkps")
    public @ResponseBody CheckSaveDto<User> checkps(HttpServletResponse response,
                                                    HttpServletRequest request,
                                                    @RequestParam("userId")Integer userId,
                                                    @RequestParam("password")String password){
        CheckSaveDto<User> checkSaveDto = new CheckSaveDto<>();
        User user1 = userService.getOneByUserId(userId);
        if(!user1.getPassword().equals(password)){
            checkSaveDto.setState(1);
            checkSaveDto.setMes("原密码输入错误...");
            return checkSaveDto;
        }
        return checkSaveDto;
    }
}
