package com.lu.office.service.repositoryimpl.car;

import com.lu.office.model.car.Parking;
import com.lu.office.model.sys.Page;
import com.lu.office.model.sys.User;
import com.lu.office.service.dao.car.ParkingMapper;
import com.lu.office.service.dao.sys.UserMapper;
import com.lu.office.service.repository.car.CarService;
import com.lu.office.service.utile.WebUtile;
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

    @Autowired
    private UserMapper userMapper;

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
        list = this.getTotal(list);
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

    @Override
    public User getOneUserByName(String rentor) {
        User user = userMapper.getOneByUserName(rentor);
        return user;
    }

    @Override
    public int rentOneParking(Parking parking) {
        User user = userMapper.getOneByUserName(parking.getUser().getUserName());
        parking.setUserId(user.getUserId());
        parking.setStatus(1);
        parking.setRentTime(new Date());
        int num = parkingMapper.updateByPrimaryKeySelective(parking);
        return num;
    }

    @Override
    public int recoverOne(Parking parking) {
        parking.setRentTime(new Date());
        parking.setUserId(0);
        parking.setStatus(2);
        int num = parkingMapper.updateByPrimaryKeySelective(parking);
        return num;
    }

    @Override
    public Page<Parking> getPageList(int page, int pageSize, String keywork, Integer keyType) {
        keywork = keywork.trim();
        StringBuffer cont =new StringBuffer();
        if(keyType != 0){
            cont.append(" and status = "+keyType);
        }
        StringBuffer buffer1 = new StringBuffer(cont);
        StringBuffer buffer2 = new StringBuffer(cont);
        int offSet = (page-1)*pageSize;
        int count = 0;
        List<Parking> list = new ArrayList<>();
        if(!"".equals(keywork)){
            buffer1.append(" and parking_id = \'"+keywork+"\'");
            count = parkingMapper.getCountBycont(buffer1.toString());
            list = parkingMapper.getPageListByCont(offSet,pageSize,buffer1.toString());
            if(!"".equals(keywork) && count == 0){
                buffer2.append(" and adress like \'%"+keywork+"%\'");
                count = parkingMapper.getCountBycont(buffer2.toString());
                list = parkingMapper.getPageListByCont(offSet,pageSize,buffer2.toString());
            }
        }else{
            count = parkingMapper.getCountBycont(cont.toString());
            list = parkingMapper.getPageListByCont(offSet,pageSize,cont.toString());
        }
        list = this.getTotal(list);
        Page<Parking> parkingPage = new Page<>(list,page,pageSize,count);
        return parkingPage;
    }

    private List<Parking> getTotal(List<Parking> list){
        int size = list.size();
        Parking parking = null;
        for(int i=0;i<size;i++){
            parking = list.get(i);
            int type = parking.getPrinceType();
            int prince = parking.getPrince();
            int timeOfTwo;
            if(type == 1){
                timeOfTwo = WebUtile.hoursofTwo(parking.getRentTime(),new Date());
            }else if(type == 2){
                timeOfTwo = WebUtile.daysOfTwo(parking.getRentTime(),new Date());
            }else if(type == 3){
                timeOfTwo = WebUtile.monthesOfTwo(parking.getRentTime(),new Date());
            }else if(type == 4){
                timeOfTwo = WebUtile.yearsOfTwo(parking.getRentTime(),new Date());
            }else{
                timeOfTwo=0;
            }
            parking.setTotal(timeOfTwo*prince);
        }
        return list;
    }
}
