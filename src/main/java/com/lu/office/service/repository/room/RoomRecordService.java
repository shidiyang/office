package com.lu.office.service.repository.room;

import com.lu.office.model.room.RoomRecord;
import com.lu.office.model.sys.Page;

/**
 * Created by user on 5/21/17.
 */
public interface RoomRecordService {
    Page<RoomRecord> getRecordPageList(int page, int pageSize, String keyword, Integer keyType);
}
