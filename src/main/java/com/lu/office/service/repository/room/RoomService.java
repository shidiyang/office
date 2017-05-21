package com.lu.office.service.repository.room;

import com.lu.office.model.car.Parking;
import com.lu.office.model.room.Room;
import com.lu.office.model.sys.Page;
import com.lu.office.model.sys.User;

/**
 * Created by user on 5/21/17.
 */
public interface RoomService {
    Page<Room> getPageList(int page, int pageSize, String keyword);

    Room getOneByRoomId(Room room);

    Room getOneByRoomName(Room room);

    int saveOrUpdateOne(Room room);

    int deleteOneById(Integer id);

    Page<Room> getPageList(int page, int pageSize, String keyword, Integer keyType);

    User getOneUserByName(String rentor);

    int rentOneRoom(Room room);

    Room getOneRoomById(Integer id);

    int recoverOne(Room room);

    Page<Room> getPageListByUserName(int page, int pageSize, String keyword, String userName);
}
