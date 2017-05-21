package com.lu.office.controller.charge;

import com.lu.office.controller.dto.CheckSaveDto;
import com.lu.office.model.charge.Charge;
import com.lu.office.model.sys.Page;
import com.lu.office.service.repository.charge.ChargeService;
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
 * Created by user on 5/20/17.
 */
@Controller
@RequestMapping("/charge")
public class ChargeController {

    @Autowired
    private ChargeService chargeService;

    @RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestParam(value = "keyword",defaultValue = "")String keyword,
                             @RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "pageSize", defaultValue = "15") int pageSize){
        ModelAndView mv = new ModelAndView("/charge/charge");
        Page<Charge> staffPage = chargeService.getPageList(page,pageSize,keyword);
        mv.addObject("page",staffPage);
        mv.addObject("keyword",keyword);
        return mv;
    }

    @RequestMapping("/check")
    public @ResponseBody
    CheckSaveDto<Charge> check(HttpServletRequest request,
                              HttpServletResponse response,
                              Charge charge) throws ParseException {
        CheckSaveDto<Charge> checkSaveDto = new CheckSaveDto<>();
        Charge charge1 = chargeService.getOneByAll(charge);
        if(charge1 != null){
            checkSaveDto.setState(1);
            checkSaveDto.setMes("此收费方式已经存在,请修改后添加.");
            return checkSaveDto;
        }
        checkSaveDto.setState(0);
        return checkSaveDto;
    }

    @RequestMapping("/save")
    public @ResponseBody boolean save(HttpServletRequest request,
                                      HttpServletResponse response,
                                      Charge charge){
        int num = chargeService.saveOrUpdateOne(charge);
        if(num > 0){
            return true;
        }
        return false;
    }

    @RequestMapping("/del")
    public @ResponseBody CheckSaveDto<Charge> delete(HttpServletRequest request,
                                                    HttpServletResponse response,
                                                    @RequestParam("id")Integer id){
        CheckSaveDto<Charge> checkSaveDto = new CheckSaveDto<>();
        int num = chargeService.deleteOneById(id);
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
