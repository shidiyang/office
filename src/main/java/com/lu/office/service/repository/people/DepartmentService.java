package com.lu.office.service.repository.people;

import com.lu.office.model.people.Department;
import com.lu.office.model.sys.Page;

/**
 * Created by user on 5/18/17.
 */
public interface DepartmentService {
    Page<Department> getPageList(int page, int pageSize, String keyword);

    Department getOneToSaveOrUpdate(Department department);

    int saveOrUpdateOne(Department department);

    int deleteOneById(Integer id);
}
