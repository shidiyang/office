package com.lu.office.service.repository.car;

import com.lu.office.model.car.Parking;
import com.lu.office.model.sys.Page;

/**
 * Created by user on 5/16/17.
 */
public interface CarService {
    Page<Parking> getPageList(int page, int pageSize, String keywork);

    Parking getOneByParkingId(Parking parking);

    int saveOrUpdateOne(Parking parking);

    int deleteOneById(Integer id);
}
