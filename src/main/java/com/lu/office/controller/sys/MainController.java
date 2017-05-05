package com.lu.office.controller.sys;

import com.alibaba.fastjson.JSON;
import com.lu.office.model.sys.Menu;
import com.lu.office.service.repository.sys.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by user on 5/4/17.
 */@Controller
@RequestMapping("/main")
public class MainController {

     @Autowired
    private MenuService menuService;

     @RequestMapping("/index")
    public ModelAndView getList(HttpServletResponse response,
                                HttpServletRequest request
                                ) throws IOException {
         ModelAndView mv = new ModelAndView("sys/index");
         List<Menu> menus = menuService.getMenus();
         String js=  JSON.toJSONString(menus);
         System.out.println("这是一次调试"+js);
         mv.addObject("treeMenu",menus);
         return mv;
    }
}
