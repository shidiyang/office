package com.lu.office.service.repositoryimpl.car;

import com.lu.office.model.car.ParkingRecord;
import com.lu.office.model.sys.Page;
import com.lu.office.service.dao.car.ParkingRecordMapper;
import com.lu.office.service.repository.car.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 5/17/17.
 */
@Repository("recordService")
public class RecordServiceImpl implements RecordService {

    @Autowired
    private ParkingRecordMapper parkingRecordMapper;
    @Override
    public Page<ParkingRecord> getRecordPageList(int page, int pageSize, String keyword, Integer keyType) {
        keyword = keyword.trim();
        StringBuffer cont =new StringBuffer();
        if(keyType != 0){
            cont.append(" and operate_type = "+keyType);
        }
        StringBuffer buffer1 = new StringBuffer(cont);
        StringBuffer buffer2 = new StringBuffer(cont);
        StringBuffer buffer3 = new StringBuffer(cont);
        int offSet = (page-1)*pageSize;
        int count = 0;
        List<ParkingRecord> list = new ArrayList<>();
        if(!"".equals(keyword)){
            buffer1.append(" and parking_id = \'"+keyword+"\'");
            count = parkingRecordMapper.getCountBycont(buffer1.toString());
            list = parkingRecordMapper.getPageListByCont(offSet,pageSize,buffer1.toString());
            if(!"".equals(keyword) && count == 0){
                buffer2.append(" and user_name = \'"+keyword+"\'");
                count = parkingRecordMapper.getCountWithName(buffer2.toString());
                list = parkingRecordMapper.getPageListWithName(offSet,pageSize,buffer2.toString());
                if(!"".equals(keyword) && count == 0) {
                    buffer3.append(" and parking_adress like \'%" + keyword + "%\'");
                    count = parkingRecordMapper.getCountBycont(buffer3.toString());
                    list = parkingRecordMapper.getPageListByCont(offSet, pageSize, buffer3.toString());
                }
            }
        }else{
            count = parkingRecordMapper.getCountBycont(cont.toString());
            list = parkingRecordMapper.getPageListByCont(offSet,pageSize,cont.toString());
        }
        Page<ParkingRecord> parkingPage = new Page<>(list,page,pageSize,count);
        return parkingPage;
    }

}
