package com.lu.office.controller.room;

import com.lu.office.controller.dto.CheckSaveDto;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by user on 5/21/17.
 */
@Controller
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestParam(value = "keyword",defaultValue = "")String keyword,
                             @RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "pageSize", defaultValue = "15") int pageSize){
        ModelAndView mv = new ModelAndView("/room/room");
        Page<Room> parkingPage = roomService.getPageList(page,pageSize,keyword);
        mv.addObject("page",parkingPage);
        mv.addObject("keyword",keyword);
        return mv;
    }

    @RequestMapping("/check")
    public @ResponseBody
    CheckSaveDto<Room> check(HttpServletResponse response,
                                HttpServletRequest request,
                                Room room){
        CheckSaveDto<Room> checkSaveDto = new CheckSaveDto<>();
        Room room1 = roomService.getOneByRoomId(room);
        if(room1 != null){
            checkSaveDto.setState(1);
            checkSaveDto.setMes(room.getRoomId()+" 已经被使用,请进行修改.");
            return checkSaveDto;
        }
        room1 = roomService.getOneByRoomName(room);
        if(room1 != null){
            checkSaveDto.setState(1);
            checkSaveDto.setMes(room.getRoomName()+" :已经存在,请进行修改.");
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
                                         Room room){
        room.setOperator(WebUtile.decodeBase64(userName));
        int num = roomService.saveOrUpdateOne(room);
        if(num >0){
            return true;
        }
        return false;
    }

    @RequestMapping("/del")
    public @ResponseBody CheckSaveDto<Room> delete(HttpServletRequest request,
                                                      HttpServletResponse response,
                                                      @RequestParam("id")Integer id){
        CheckSaveDto<Room> checkSaveDto = new CheckSaveDto();
        int num = roomService.deleteOneById(id);
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
