package com.lu.office.service.repositoryimpl.people;

import com.lu.office.model.car.Parking;
import com.lu.office.model.people.Department;
import com.lu.office.model.sys.Page;
import com.lu.office.service.dao.people.DepartmentMapper;
import com.lu.office.service.repository.people.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 5/18/17.
 */
@Repository("departmentService")
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public Page<Department> getPageList(int page, int pageSize, String keyword) {
        keyword = keyword.trim();
        String cont = "";
        int offSet = (page-1)*pageSize;
        int count = 0;
        List<Department> list = new ArrayList<>();
        if(!"".equals(keyword)){
            cont = " and department_name like \'%"+keyword+"%\'";
        }
        count = departmentMapper.getCountBycont(cont);
        list = departmentMapper.getPageListByCont(offSet,pageSize,cont);
        Page<Department> parkingPage = new Page<>(list,page,pageSize,count);
        return parkingPage;
    }

    @Override
    public Department getOneToSaveOrUpdate(Department department) {
        Integer id = department.getId();
        StringBuffer cont = new StringBuffer(" and department_name = \'"+department.getDepartmentName()+"\'");
        if(id != null){
            cont.append(" and id != "+id);
        }
        Department department1 = departmentMapper.getOneByCont(cont.toString());
        return department1;
    }

    @Override
    public int saveOrUpdateOne(Department department) {
        Integer id = department.getId();
        int num;
        if(id == null){
            num = departmentMapper.insertSelective(department);
        }else{
            num = departmentMapper.updateByPrimaryKeySelective(department);
        }
        return num;
    }

    @Override
    public int deleteOneById(Integer id) {
        int num = departmentMapper.deleteByPrimaryKey(id);
        return num;
    }
}
