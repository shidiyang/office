package com.lu.office.controller.sys;

import com.lu.office.controller.dto.CheckSaveDto;
import com.lu.office.model.sys.Page;
import com.lu.office.model.sys.Permission;
import com.lu.office.model.sys.Roles;
import com.lu.office.model.sys.RolesPermissionKey;
import com.lu.office.service.repository.sys.RolesSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by user on 5/12/17.
 */
@Controller
@RequestMapping("/role")
public class RolesController {

    @Autowired
    private RolesSevice rolesSevice;

    @RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestParam(value = "keyword",defaultValue = "")String keyword,
                             @RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        ModelAndView mv = new ModelAndView("/sys/roles");
        Page<RolesPermissionKey> page1 = rolesSevice.getPageList(page,pageSize,keyword);
        List<Permission> list = rolesSevice.getPermissionList();
        mv.addObject("keyword",keyword);
        mv.addObject("page",page1);
        mv.addObject("permissions",list);
        return mv;
    }

    @RequestMapping("/check")
    public @ResponseBody
    CheckSaveDto<RolesPermissionKey> check(HttpServletResponse response,
                                           HttpServletRequest request,
                                           RolesPermissionKey rolesPermissionKey){
        CheckSaveDto<RolesPermissionKey> checkSaveDto = new CheckSaveDto<>();
        Roles roles = rolesSevice.getRoleByName(rolesPermissionKey.getRoles().getDescription());
        if(roles == null){
            rolesSevice.insertOne(rolesPermissionKey.getRoles().getDescription());
            checkSaveDto.setState(0);
            return checkSaveDto;
        }else{
            RolesPermissionKey rolesPermissionKey1 = rolesSevice.getOneByKey(rolesPermissionKey);
            if(rolesPermissionKey1 != null){
                checkSaveDto.setState(1);
                checkSaveDto.setMes(rolesPermissionKey.getRoles().getDescription()+" 已经存在此权限.");
                return checkSaveDto;
            }else {
                checkSaveDto.setState(0);
                return checkSaveDto;
            }
        }
    }

    @RequestMapping("/save")
    public @ResponseBody boolean save(HttpServletRequest request,
                                      HttpServletResponse response,
                                      RolesPermissionKey rolesPermissionKey){
        int num = 0;
        num = rolesSevice.saveOneRolesPermissionKey(rolesPermissionKey);
        if(num >0 ){
            return true;
        }
        return false;
    }

    @RequestMapping("/del")
    public @ResponseBody CheckSaveDto<RolesPermissionKey> delete(HttpServletResponse response,
                                                                 HttpServletRequest request,
                                                                 @RequestParam("roleId")Integer roleId,
                                                                 @RequestParam("permissionId")Integer permissionId){
        CheckSaveDto<RolesPermissionKey> checkSaveDto = new CheckSaveDto<>();
        int num = rolesSevice.deleteOneByKey(roleId,permissionId);
        if(num >0){
            checkSaveDto.setState(0);
            checkSaveDto.setMes("删除成功.");
            return checkSaveDto;
        }else {
            checkSaveDto.setState(1);
            checkSaveDto.setMes("删除失败.");
            return checkSaveDto;
        }
    }

}
