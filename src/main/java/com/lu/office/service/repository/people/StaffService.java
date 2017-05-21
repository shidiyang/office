package com.lu.office.service.repository.people;

import com.lu.office.model.people.Department;
import com.lu.office.model.people.Staff;
import com.lu.office.model.sys.Page;

import java.util.List;

/**
 * Created by user on 5/19/17.
 */
public interface StaffService {
    Page<Staff> getPageList(int page, int pageSize, String keyword);

    List<Department> getDepartmentList();

    Staff getOneByStaffId(Staff staff);

    int saveOrUpdateOne(Staff staff);

    int deleteOneById(Integer id);
}
