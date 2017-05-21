package com.lu.office.service.repositoryimpl.Room;

import com.lu.office.model.car.ParkingRecord;
import com.lu.office.model.room.RoomRecord;
import com.lu.office.model.sys.Page;
import com.lu.office.service.dao.room.RoomRecordMapper;
import com.lu.office.service.repository.room.RoomRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 5/21/17.
 */
@Repository("roomRecordService")
public class RoomRecordServiceImpl implements RoomRecordService{

    @Autowired
    private RoomRecordMapper roomRecordMapper;

    @Override
    public Page<RoomRecord> getRecordPageList(int page, int pageSize, String keyword, Integer keyType) {
        keyword = keyword.trim();
        StringBuffer cont =new StringBuffer();
        if(keyType != 0){
            cont.append(" and opertate_type = "+keyType);
        }
        StringBuffer buffer1 = new StringBuffer(cont);
        StringBuffer buffer2 = new StringBuffer(cont);
        StringBuffer buffer3 = new StringBuffer(cont);
        int offSet = (page-1)*pageSize;
        int count = 0;
        List<RoomRecord> list = new ArrayList<>();
        if(!"".equals(keyword)){
            buffer1.append(" and room_id = \'"+keyword+"\'");
            count = roomRecordMapper.getCountBycont(buffer1.toString());
            list = roomRecordMapper.getPageListByCont(offSet,pageSize,buffer1.toString());
            if(!"".equals(keyword) && count == 0){
                buffer2.append(" and user_name = \'"+keyword+"\'");
                count = roomRecordMapper.getCountWithName(buffer2.toString());
                list = roomRecordMapper.getPageListWithName(offSet,pageSize,buffer2.toString());
                if(!"".equals(keyword) && count == 0) {
                    buffer3.append(" and room_name like \'%" + keyword + "%\'");
                    count = roomRecordMapper.getCountBycont(buffer3.toString());
                    list = roomRecordMapper.getPageListByCont(offSet, pageSize, buffer3.toString());
                }
            }
        }else{
            count = roomRecordMapper.getCountBycont(cont.toString());
            list = roomRecordMapper.getPageListByCont(offSet,pageSize,cont.toString());
        }
        Page<RoomRecord> roomRecordPage = new Page<>(list,page,pageSize,count);
        return roomRecordPage;
    }

}

