package com.lu.office.controller.people;

import com.lu.office.controller.dto.CheckSaveDto;
import com.lu.office.model.people.Department;
import com.lu.office.model.sys.Page;
import com.lu.office.service.repository.people.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import scala.collection.generic.BitOperations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by user on 5/18/17.
 */
@Controller
@RequestMapping("/post")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestParam(value = "keyword",defaultValue = "")String keyword,
                             @RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        ModelAndView mv = new ModelAndView("/people/department");
        Page<Department> departmentPage = departmentService.getPageList(page,pageSize,keyword);
        mv.addObject("keyword",keyword);
        mv.addObject("page",departmentPage);
        return mv;
    }

    @RequestMapping("/check")
    public @ResponseBody
    CheckSaveDto<Department> check(HttpServletResponse response,
                                   HttpServletRequest request,
                                   Department department){
        CheckSaveDto<Department> checkSaveDto = new CheckSaveDto<>();
        Department department1 = departmentService.getOneToSaveOrUpdate(department);
        if(department1 == null){
            checkSaveDto.setState(0);
            return checkSaveDto;
        }else{
            checkSaveDto.setState(1);
            checkSaveDto.setMes(department.getDepartmentName()+" 部门已经存在,请修改后操作.");
            return checkSaveDto;
        }
    }

    @RequestMapping("/save")
    public @ResponseBody boolean save(HttpServletResponse response,
                                      HttpServletRequest request,
                                      Department department){
        int num = departmentService.saveOrUpdateOne(department);
        if(num > 0){
            return true;
        }
        return false;
    }

    @RequestMapping("/del")
    public @ResponseBody CheckSaveDto<Department> delete(HttpServletResponse response,
                                                         HttpServletRequest request,
                                                         @RequestParam("id")Integer id){
        CheckSaveDto<Department> checkSaveDto =  new CheckSaveDto<>();
        int num = departmentService.deleteOneById(id);
        if(num > 0){
            checkSaveDto.setState(0);
            checkSaveDto.setMes("删除成功.");
            return checkSaveDto;
        }else{
            checkSaveDto.setState(1);
            checkSaveDto.setMes("删除失败.");
            return checkSaveDto;
        }
    }

}
