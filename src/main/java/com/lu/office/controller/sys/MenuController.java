package com.lu.office.controller.sys;

import com.lu.office.controller.dto.CheckSaveDto;
import com.lu.office.model.sys.Menu;
import com.lu.office.model.sys.Page;
import com.lu.office.model.sys.Permission;
import com.lu.office.service.repository.sys.MenuService;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by user on 5/6/17.
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/list")
    public ModelAndView list(HttpServletResponse response,
                             HttpServletRequest request,
                             @RequestParam(value = "keyword",defaultValue = "")String keywork,
                             @RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        ModelAndView mv = new ModelAndView("/sys/menu");
        Integer parentId =null;
        if(!keywork.equals("")){
            parentId = Integer.valueOf(keywork);
        }
        Page<Menu> page1 = menuService.getPageList(page,pageSize,parentId);
        mv.addObject("keyword",keywork);
        mv.addObject("page",page1);
        return mv;
    }

    @RequestMapping("/check")
    public @ResponseBody CheckSaveDto<Menu> check(HttpServletRequest request,
                                                  HttpServletResponse response,
                                                  Menu menu){
        CheckSaveDto<Menu> checkSaveDto = new CheckSaveDto<>();
        Menu menu1 = menuService.getOneById(menu);
        if(menu1 != null){
            checkSaveDto.setState(1);
            checkSaveDto.setMes("菜单ID: "+menu.getId()+" 已经存在,请修改后添加.");
            return checkSaveDto;
        }
        menu1 = menuService.getOneByName(menu);
        if(menu1 != null){
            checkSaveDto.setState(1);
            checkSaveDto.setMes("菜单名称: "+menu.getText()+" 已经存在，请修改后添加.");
            return checkSaveDto;
        }else{
            checkSaveDto.setState(0);
            return checkSaveDto;
        }
    }

    @RequestMapping("/save")
    public @ResponseBody Boolean save(HttpServletResponse response,
                                      HttpServletRequest request,
                                      Menu menu){
        int num = menuService.saveOneMenu(menu);
        if(num >0){
            return true;
        }
        return false;
    }
}
