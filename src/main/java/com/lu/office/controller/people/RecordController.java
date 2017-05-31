package com.lu.office.controller.people;

import com.lu.office.model.people.Record;
import com.lu.office.model.sys.Page;
import com.lu.office.service.repository.people.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by user on 5/22/17.
 */
@Controller
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestParam(value = "startTime",defaultValue = "")String startTime,
                             @RequestParam(value = "stopTime",defaultValue = "")String stopTime,
                             @RequestParam(value = "keyword",defaultValue = "")String keyword,
                             @RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "pageSize", defaultValue = "15") int pageSize){
        ModelAndView mv = new ModelAndView("/people/staff");
        Page<Record> staffPage = recordService.getPageList(page,pageSize,keyword,startTime,stopTime);
        mv.addObject("page",staffPage);
        mv.addObject("keyword",keyword);
        return mv;
    }
}
