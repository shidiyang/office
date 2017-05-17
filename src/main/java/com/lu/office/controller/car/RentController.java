package com.lu.office.controller.car;

import com.lu.office.controller.dto.CheckSaveDto;
import com.lu.office.model.car.Parking;
import com.lu.office.model.sys.Page;
import com.lu.office.model.sys.User;
import com.lu.office.service.repository.car.CarService;
import com.lu.office.service.utile.WebUtile;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import scala.Int;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by user on 5/16/17.
 */
@Controller
@RequestMapping("/crent")
public class RentController {

    @Autowired
    private CarService carService;

    @RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestParam(value = "keyword",defaultValue = "")String keywork,
                             @RequestParam(value = "keyType",defaultValue = "0")Integer keyType,
                             @RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        ModelAndView mv = new ModelAndView("/car/rent");
        Page<Parking> parkingPage = carService.getPageList(page,pageSize,keywork,keyType);
        mv.addObject("page",parkingPage);
        mv.addObject("keyword",keywork);
        mv.addObject("keyType",keyType);
        return mv;

    }

    @RequestMapping("/check")
    public @ResponseBody
    CheckSaveDto<Parking> check(HttpServletRequest request,
                                HttpServletResponse response,
                                @RequestParam("rentor")String rentor){
        CheckSaveDto<Parking> checkSaveDto = new CheckSaveDto<>();
        User user = carService.getOneUserByName(rentor);
        if(user == null){
            checkSaveDto.setState(1);
            checkSaveDto.setMes(rentor+" 用户不存在,请先添加用户.");
            return checkSaveDto;
        }else{
            checkSaveDto.setState(0);
            return checkSaveDto;
        }
    }

    @RequestMapping("/save")
    public @ResponseBody boolean save(HttpServletRequest request,
                                      HttpServletResponse response,
                                      @CookieValue("userName")String userName,
                                      @RequestParam("id") Integer id,
                                      @RequestParam("rentor")String rentor){
        Parking parking = new Parking();
        User user = new User();
        user.setUserName(rentor);
        parking.setOprator(WebUtile.decodeBase64(userName));
        parking.setUser(user);
        parking.setId(id);
        int num = carService.rentOneParking(parking);
        if(num>0) return true;
        return false;

    }

    @RequestMapping("/recover")
    public @ResponseBody CheckSaveDto<Parking> recover(HttpServletRequest request,
                                                       HttpServletResponse response,
                                                       @CookieValue("userName")String userName,
                                                       @RequestParam("id") Integer id,
                                                       @RequestParam("total")Integer total){
        Parking parking = carService.getOneParkingById(id);
        CheckSaveDto<Parking> checkSaveDto = new CheckSaveDto<>();
        parking.setOprator(WebUtile.decodeBase64(userName));
        parking.setTotal(total);
        int num = carService.recoverOne(parking);
        if(num>0){
            checkSaveDto.setState(0);
            checkSaveDto.setMes("收回成功.");
            return checkSaveDto;
        }else{
            checkSaveDto.setState(1);
            checkSaveDto.setMes("收回失败.");
            return checkSaveDto;
        }
    }
}
