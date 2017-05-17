package com.lu.office.service.repository.car;

import com.lu.office.model.car.ParkingRecord;
import com.lu.office.model.sys.Page;

/**
 * Created by user on 5/17/17.
 */
public interface RecordService {
    Page<ParkingRecord> getRecordPageList(int page, int pageSize, String keyword, Integer keyType);
}
