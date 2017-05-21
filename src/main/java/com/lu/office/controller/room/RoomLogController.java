package com.lu.office.controller.room;

import com.lu.office.model.car.ParkingRecord;
import com.lu.office.model.room.RoomRecord;
import com.lu.office.model.sys.Page;
import com.lu.office.service.repository.room.RoomRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by user on 5/21/17.
 */
@Controller
@RequestMapping("/rrecord")
public class RoomLogController {

    @Autowired
    private RoomRecordService roomRecordService;

    @RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestParam(value = "keyword", defaultValue = "") String keyword,
                             @RequestParam(value = "keyType", defaultValue = "0") Integer keyType,
                             @RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "pageSize", defaultValue = "15") int pageSize) {
        ModelAndView mv = new ModelAndView("/room/log");
        Page<RoomRecord> recordPageList = roomRecordService.getRecordPageList(page, pageSize, keyword, keyType);
        mv.addObject("page", recordPageList);
        mv.addObject("keyword", keyword);
        mv.addObject("keyType", keyType);
        return mv;
    }
}
