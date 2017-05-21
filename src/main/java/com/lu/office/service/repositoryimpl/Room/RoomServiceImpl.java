package com.lu.office.service.repositoryimpl.Room;

import com.lu.office.model.car.Parking;
import com.lu.office.model.room.Room;
import com.lu.office.model.room.RoomRecord;
import com.lu.office.model.sys.Page;
import com.lu.office.model.sys.User;
import com.lu.office.service.dao.room.RoomMapper;
import com.lu.office.service.dao.room.RoomRecordMapper;
import com.lu.office.service.dao.sys.UserMapper;
import com.lu.office.service.repository.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 5/21/17.
 */
@Repository("/roomService")
public class RoomServiceImpl implements RoomService{

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoomRecordMapper roomRecordMapper;

    @Override
    public Page<Room> getPageList(int page, int pageSize, String keyword) {
        keyword = keyword.trim();
        String cont = "";
        int offSet = (page-1)*pageSize;
        int count = 0;
        List<Room> list = new ArrayList<>();
        if(!"".equals(keyword)){
            cont = " and room_id = \'"+keyword+"\'";
            count = roomMapper.getCountBycont(cont);
        }
        if(!"".equals(keyword) && count == 0){
            cont = " and room_name like \'%"+keyword+"%\'";
        }
        count = roomMapper.getCountBycont(cont);
        list = roomMapper.getPageListByCont(offSet,pageSize,cont);
        Page<Room> roomPage = new Page<>(list,page,pageSize,count);
        return roomPage;
    }

    @Override
    public Room getOneByRoomId(Room room) {
        Integer id = room.getId();
        StringBuffer cont = new StringBuffer(" and room_id = "+room.getRoomId());
        if(id != null){
            cont.append(" and id !="+id);
        }
        Room room1= roomMapper.getOneByCont(cont.toString());
        return room1;
    }

    @Override
    public Room getOneByRoomName(Room room) {
        Integer id = room.getId();
        StringBuffer cont = new StringBuffer(" and room_name = \'"+room.getRoomName()+"\'");
        if(id != null){
            cont.append(" and id !="+id);
        }
        Room room1= roomMapper.getOneByCont(cont.toString());
        return room1;
    }

    @Override
    public int saveOrUpdateOne(Room room) {
        Integer id = room.getId();
        room.setRentTime(new Date());
        int num = 0;
        if(id == null){
            num = roomMapper.insert(room);
        }else{
            num = roomMapper.updateByPrimaryKeySelective(room);
        }
        return num;
    }

    @Override
    public int deleteOneById(Integer id) {
        int num = roomMapper.deleteByPrimaryKey(id);
        return num;
    }

    @Override
    public Page<Room> getPageList(int page, int pageSize, String keyword, Integer keyType) {
        keyword = keyword.trim();
        StringBuffer cont =new StringBuffer();
        if(keyType != 0){
            cont.append(" and status = "+keyType);
        }
        StringBuffer buffer1 = new StringBuffer(cont);
        StringBuffer buffer2 = new StringBuffer(cont);
        int offSet = (page-1)*pageSize;
        int count = 0;
        List<Room> list = new ArrayList<>();
        if(!"".equals(keyword)){
            buffer1.append(" and room_id = \'"+keyword+"\'");
            count = roomMapper.getCountBycont(buffer1.toString());
            list = roomMapper.getPageListByCont(offSet,pageSize,buffer1.toString());
            if(!"".equals(keyword) && count == 0){
                buffer2.append(" and room_name like \'%"+keyword+"%\'");
                count = roomMapper.getCountBycont(buffer2.toString());
                list = roomMapper.getPageListByCont(offSet,pageSize,buffer2.toString());
            }
        }else{
            count = roomMapper.getCountBycont(cont.toString());
            list = roomMapper.getPageListByCont(offSet,pageSize,cont.toString());
        }
        Page<Room> parkingPage = new Page<>(list,page,pageSize,count);
        return parkingPage;
    }

    @Override
    public User getOneUserByName(String rentor) {
        User user = userMapper.getOneByUserName(rentor);
        return user;
    }

    @Override
    public int rentOneRoom(Room room) {
        User user = userMapper.getOneByUserName(room.getUser().getUserName());
        room.setUserId(user.getUserId());
        room.setRentTime(new Date());
        room.setStatus(1);
        this.saveOneRoomRecord(room);
        int num = roomMapper.updateByPrimaryKeySelective(room);
        return num;
    }

    @Override
    public Room getOneRoomById(Integer id) {
        Room room = roomMapper.selectByPrimaryKey(id);
        return room;
    }

    @Override
    public int recoverOne(Room room) {
        room.setRentTime(new Date());
        room.setStatus(2);
        this.saveOneRoomRecord(room);
        room.setUserId(0);
        room.setRoomUse("");
        int num = roomMapper.updateByPrimaryKeySelective(room);
        return num;
    }

    @Override
    public Page<Room> getPageListByUserName(int page, int pageSize, String keyword, String userName) {
        keyword = keyword.trim();
        StringBuffer cont = new StringBuffer(" and status = 1 and user_name = \'"+userName+"\'");
        StringBuffer buffer1 = new StringBuffer(cont);
        StringBuffer buffer2 = new StringBuffer(cont);
        int offSet = (page-1)*pageSize;
        int count = 0;
        List<Room> list = new ArrayList<>();
        if(!"".equals(keyword)){
            buffer1.append(" and room_id = \'"+keyword+"\'");
            count = roomMapper.getCountBycontWithName(buffer1.toString());
            list = roomMapper.getPageListByContWithName(offSet,pageSize,cont.toString());
            if(!"".equals(keyword) && count == 0){
                buffer2.append(" and room_name like \'%"+keyword+"%\'");
                count = roomMapper.getCountBycontWithName(buffer2.toString());
                list = roomMapper.getPageListByContWithName(offSet,pageSize,buffer2.toString());
            }
        }else{
            count = roomMapper.getCountBycontWithName(cont.toString());
            list = roomMapper.getPageListByContWithName(offSet,pageSize,cont.toString());
        }
        Page<Room> roomPage = new Page<>(list,page,pageSize,count);
        return roomPage;
    }

    private int saveOneRoomRecord(Room room){
        Room room1 = roomMapper.selectByPrimaryKey(room.getId());
        RoomRecord roomRecord = new RoomRecord();
        roomRecord.setOperateTime(room.getRentTime());
        roomRecord.setOperator(room.getOperator());
        roomRecord.setOpertateType(room.getStatus());
        roomRecord.setRoomId(room1.getRoomId());
        roomRecord.setRoomName(room1.getRoomName());
        roomRecord.setRentorId(room.getUserId());
        int num = roomRecordMapper.insertSelective(roomRecord);
        return num;
    }
}
