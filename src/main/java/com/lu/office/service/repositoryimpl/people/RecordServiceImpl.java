package com.lu.office.service.repositoryimpl.people;

import com.lu.office.model.people.Record;
import com.lu.office.model.sys.Page;
import com.lu.office.service.dao.people.RecordMapper;
import com.lu.office.service.repository.people.RecordService;
import com.lu.office.service.utile.WebUtile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import scala.collection.script.Start;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 5/22/17.
 */
@Repository("/recordService")
public class RecordServiceImpl implements RecordService{

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public Page<Record> getPageList(int page, int pageSize, String keyword, String startTime, String stopTime) {
        keyword = keyword.trim();
        int offSet = (page-1)*pageSize;
        Date start = null;
        Date stop = null;
        try {
            if(!startTime.trim().equals("")){
                start = WebUtile.dateTranst(startTime);
            }
            if(!stopTime.trim().equals("")){
                stop = WebUtile.dateTranstAndAddOneDay(stopTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuffer cont = new StringBuffer("");
        if(start != null){
            cont.append(" and creat_time >="+start);
        }
        if(stop != null){
            cont.append(" and creat_time <"+stop);
        }
        StringBuffer buffer1 = new StringBuffer(cont);
        StringBuffer buffer2 = new StringBuffer(cont);
        int count = 0;
        List<Record>  list = new ArrayList();
        if(!"".equals(keyword)){
            buffer1.append(" and r.staff_id = \'"+keyword+"\'");
            count = recordMapper.getCountByCont(buffer1.toString());
            list = recordMapper.getListByCont(offSet,pageSize,buffer1.toString());
            if(count <=0){
                buffer2.append(" and staff_name like \'%"+keyword+"%\'");
                count = recordMapper.getCountByContWithName(buffer2.toString());
                list = recordMapper.getListByContWithName(offSet,pageSize,buffer2);
            }
        }else{
            count = recordMapper.getCountByCont(cont.toString());
            list = recordMapper.getListByCont(offSet,pageSize,cont.toString());
        }
        Page<Record> recordPage = new Page<>(list,page,pageSize,count);
        return recordPage;
    }
}
