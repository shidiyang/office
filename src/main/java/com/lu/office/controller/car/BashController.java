package com.lu.office.controller.car;

import com.lu.office.controller.dto.CheckSaveDto;
import com.lu.office.model.car.Parking;
import com.lu.office.model.sys.Page;
import com.lu.office.service.repository.car.CarService;
import com.lu.office.service.utile.WebUtile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by user on 5/16/17.
 */
@Controller
@RequestMapping("/cbash")
public class BashController {

    @Autowired
    private CarService carService;

    @RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestParam(value = "keyword",defaultValue = "")String keywork,
                             @RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "pageSize", defaultValue = "15") int pageSize){
        ModelAndView mv = new ModelAndView("/car/bash");
        Page<Parking> parkingPage = carService.getPageList(page,pageSize,keywork);
        mv.addObject("page",parkingPage);
        mv.addObject("keyword",keywork);
        return mv;
    }

    @RequestMapping("/check")
    public @ResponseBody
    CheckSaveDto<Parking> check(HttpServletResponse response,
                                HttpServletRequest request,
                                Parking parking){
        CheckSaveDto<Parking> checkSaveDto = new CheckSaveDto<>();
        Parking parking1 = carService.getOneByParkingId(parking);
        if(parking1 != null){
            checkSaveDto.setState(1);
            checkSaveDto.setMes(parking1.getParkingId()+" 已经被使用,请进行修改.");
            return checkSaveDto;
        }else{
            checkSaveDto.setState(0);
            return checkSaveDto;
        }
    }

    @RequestMapping("/save")
    public @ResponseBody boolean saveOne(HttpServletRequest request,
                                         HttpServletResponse response,
                                         @CookieValue("userName")String userName,
                                         Parking parking){
        parking.setOprator(WebUtile.decodeBase64(userName));
        int num = carService.saveOrUpdateOne(parking);
        if(num >0){
            return true;
        }
        return false;
    }

    @RequestMapping("/del")
    public @ResponseBody CheckSaveDto<Parking> delete(HttpServletRequest request,
                                                      HttpServletResponse response,
                                                      @RequestParam("id")Integer id){
        CheckSaveDto checkSaveDto = new CheckSaveDto();
        int num = carService.deleteOneById(id);
        if(num>0){
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
