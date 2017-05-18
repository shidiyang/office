package com.lu.office.controller.car;

import com.lu.office.model.car.ParkingRecord;
import com.lu.office.model.sys.Page;
import com.lu.office.service.repository.car.CarService;
import com.lu.office.service.repository.car.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by user on 5/16/17.
 */
@Controller
@RequestMapping("/clog")
public class LogController {

    @Autowired
    private RecordService recordService;

    @RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestParam(value = "keyword",defaultValue = "")String keyword,
                             @RequestParam(value = "keyType",defaultValue = "0")Integer keyType,
                             @RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "pageSize", defaultValue = "15") int pageSize){
        ModelAndView mv = new ModelAndView("/car/log");
        Page<ParkingRecord> parkingRecordPage = recordService.getRecordPageList(page,pageSize,keyword,keyType);
        mv.addObject("page",parkingRecordPage);
        mv.addObject("keyword",keyword);
        mv.addObject("keyType",keyType);
        return mv;

    }
}
