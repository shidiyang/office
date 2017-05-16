package com.lu.office.service.repositoryimpl.car;

import com.lu.office.model.car.Parking;
import com.lu.office.model.sys.Page;
import com.lu.office.service.dao.car.ParkingMapper;
import com.lu.office.service.repository.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 5/16/17.
 */
@Repository("carService")
public class CarServiceImpl implements CarService{

    @Autowired
    private ParkingMapper parkingMapper;

    @Override
    public Page<Parking> getPageList(int page, int pageSize, String keywork) {
        keywork = keywork.trim();
        String cont = "";
        int offSet = (page-1)*pageSize;
        int count = 0;
        List<Parking> list = new ArrayList<>();
        if(!"".equals(keywork)){
            cont = " and parking_id = \'"+keywork+"\'";
            count = parkingMapper.getCountBycont(cont);
        }
        if(!"".equals(keywork) && count == 0){
            cont = " and adress like \'%"+keywork+"%\'";
        }
        count = parkingMapper.getCountBycont(cont);
        list = parkingMapper.getPageListByCont(offSet,pageSize,cont);
        Page<Parking> parkingPage = new Page<>(list,page,pageSize,count);
        return parkingPage;
    }

    @Override
    public Parking getOneByParkingId(Parking parking) {
        Integer id = parking.getId();
        StringBuffer cont = new StringBuffer(" and parking_id = "+parking.getParkingId());
        if(id != null) {
            cont.append(" and id != "+id);
        }
        Parking parking1 = parkingMapper.getOneByCont(cont.toString());
        return parking1;
    }

    @Override
    public int saveOrUpdateOne(Parking parking) {
        Integer id = parking.getId();
        int num;
        if(id !=null){
            num = parkingMapper.updateByPrimaryKeySelective(parking);
        }else{
            parking.setStatus(2);
            parking.setRentTime(new Date());
            num = parkingMapper.insertSelective(parking);
        }
        return num;
    }

    @Override
    public int deleteOneById(Integer id) {
        int num = parkingMapper.deleteByPrimaryKey(id);
        return num;
    }
}
