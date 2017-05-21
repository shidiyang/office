package com.lu.office.controller.room;

import com.lu.office.controller.dto.CheckSaveDto;
import com.lu.office.model.room.Room;
import com.lu.office.model.sys.Page;
import com.lu.office.model.sys.User;
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
@RequestMapping("/rrent")
public class RoomRentController {

    @Autowired
    private RoomService roomService;

    @RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestParam(value = "keyword",defaultValue = "")String keyword,
                             @RequestParam(value = "keyType",defaultValue = "0")Integer keyType,
                             @RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "pageSize", defaultValue = "15") int pageSize){
        ModelAndView mv = new ModelAndView("/room/rent");
        Page<Room> roomPage = roomService.getPageList(page,pageSize,keyword,keyType);
        mv.addObject("page",roomPage);
        mv.addObject("keyword",keyword);
        mv.addObject("keyType",keyType);
        return mv;

    }

    @RequestMapping("/check")
    public @ResponseBody
    CheckSaveDto<Room> check(HttpServletRequest request,
                                HttpServletResponse response,
                                @RequestParam("rentor")String rentor){
        CheckSaveDto<Room> checkSaveDto = new CheckSaveDto<>();
        User user = roomService.getOneUserByName(rentor);
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
                                      @RequestParam("rentor")String rentor,
                                      @RequestParam("roomUses")String roomUses){
        Room room = new Room();
        User user = new User();
        user.setUserName(rentor);
        room.setOperator(WebUtile.decodeBase64(userName));
        room.setUser(user);
        room.setId(id);
        room.setRoomUse(roomUses);
        int num = roomService.rentOneRoom(room);
        if(num>0) return true;
        return false;

    }

    @RequestMapping("/recover")
    public @ResponseBody CheckSaveDto<Room> recover(HttpServletRequest request,
                                                       HttpServletResponse response,
                                                       @CookieValue("userName")String userName,
                                                       @RequestParam("id") Integer id){
        Room room = roomService.getOneRoomById(id);
        CheckSaveDto<Room> checkSaveDto = new CheckSaveDto<>();
        room.setOperator(WebUtile.decodeBase64(userName));
        int num = roomService.recoverOne(room);
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
