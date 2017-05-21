package com.lu.office.controller.room;

import com.lu.office.model.car.Parking;
import com.lu.office.model.room.Room;
import com.lu.office.model.sys.Page;
import com.lu.office.service.repository.room.RoomService;
import com.lu.office.service.utile.WebUtile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by user on 5/21/17.
 */
@Controller
@RequestMapping("/myroom")
public class MyRoomController {

    @Autowired
    private RoomService roomService;

    @RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestParam(value = "keyword",defaultValue = "")String keyword,
                             @RequestParam(value = "page", defaultValue = "1") int page,
                             @CookieValue("userName")String userName,
                             @RequestParam(value = "pageSize", defaultValue = "15") int pageSize){
        ModelAndView mv = new ModelAndView("/room/myroom");
        Page<Room> parkingPage = roomService.getPageListByUserName(page,pageSize,keyword, WebUtile.decodeBase64(userName));
        mv.addObject("keyword",keyword);
        mv.addObject("page",parkingPage);
        return mv;

    }
}
