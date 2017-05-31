package com.lu.office.controller.people;

import com.lu.office.controller.dto.CheckSaveDto;
import com.lu.office.model.people.Department;
import com.lu.office.model.people.Staff;
import com.lu.office.model.sys.Page;
import com.lu.office.service.repository.people.StaffService;
import com.lu.office.service.utile.WebUtile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;

/**
 * Created by user on 5/19/17.
 */
@Controller
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestParam(value = "keyword",defaultValue = "")String keyword,
                             @RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "pageSize", defaultValue = "15") int pageSize){
        ModelAndView mv = new ModelAndView("/people/staff");
        Page<Staff>  staffPage = staffService.getPageList(page,pageSize,keyword);
        List<Department> departmentList = staffService.getDepartmentList();
        mv.addObject("page",staffPage);
        mv.addObject("departments",departmentList);
        mv.addObject("keyword",keyword);
        return mv;
    }

    @RequestMapping("/check")
    public @ResponseBody CheckSaveDto<Staff> check(HttpServletRequest request,
                                     HttpServletResponse response,
                                     Staff staff) throws ParseException {
        CheckSaveDto<Staff> checkSaveDto = new CheckSaveDto<>();
        Staff staff1 = staffService.getOneByStaffIdWithId(staff);
        if(staff1 != null){
            checkSaveDto.setState(1);
            checkSaveDto.setMes(staff.getStaffId()+" :已经存在,请修改后添加.");
            return checkSaveDto;
        }
        if(!WebUtile.checkEmail(staff.getEmail())){
            checkSaveDto.setState(1);
            checkSaveDto.setMes("邮箱格式存在问题,请修改后添加.");
            return checkSaveDto;
        }
        if(!WebUtile.checkMobileNumber(staff.getPhone())){
            checkSaveDto.setState(1);
            checkSaveDto.setMes("电话格式存在问题,请修改后添加.");
            return checkSaveDto;
        }
        String erroString = WebUtile.IDCardValidate(staff.getIdentityId());
        if(!"".equals(erroString)){
            checkSaveDto.setState(1);
            checkSaveDto.setMes(erroString);
            return checkSaveDto;
        }
        checkSaveDto.setState(0);
        return checkSaveDto;
    }

    @RequestMapping("/save")
    public @ResponseBody boolean save(HttpServletRequest request,
                                      HttpServletResponse response,
                                      Staff staff){
        int num = staffService.saveOrUpdateOne(staff);
        if(num > 0){
            return true;
        }
        return false;
    }

    @RequestMapping("/del")
    public @ResponseBody CheckSaveDto<Staff> delete(HttpServletRequest request,
                                                    HttpServletResponse response,
                                                    @RequestParam("id")Integer id){
        CheckSaveDto<Staff> checkSaveDto = new CheckSaveDto<>();
        int num = staffService.deleteOneById(id);
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
