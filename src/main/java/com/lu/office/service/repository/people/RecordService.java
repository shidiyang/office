package com.lu.office.service.repository.people;

import com.lu.office.model.people.Record;
import com.lu.office.model.sys.Page;

/**
 * Created by user on 5/22/17.
 */
public interface RecordService {
    Page<Record> getPageList(int page, int pageSize, String keyword, String startTime, String stopTime);
}
