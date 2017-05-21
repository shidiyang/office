package com.lu.office.service.repositoryimpl.people;

import com.lu.office.model.people.Department;
import com.lu.office.model.people.Staff;
import com.lu.office.model.sys.Page;
import com.lu.office.service.dao.people.DepartmentMapper;
import com.lu.office.service.dao.people.StaffMapper;
import com.lu.office.service.repository.people.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 5/19/17.
 */
@Repository("staffService")
public class StaffServiceImpl implements StaffService{

    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public Page<Staff> getPageList(int page, int pageSize, String keyword) {
        keyword = keyword.trim();
        StringBuffer cont = new StringBuffer(" and activity = 1");
        StringBuffer buffer1 = new StringBuffer(cont);
        StringBuffer buffer2 = new StringBuffer(cont);
        int offSet = (page-1)*pageSize;
        int count = 0;
        List<Staff> list = new ArrayList<>();
        if(!"".equals(keyword)){
            buffer1.append(" and staff_id = \'"+keyword+"\'");
            count = staffMapper.getCountByCont(buffer1.toString());
            list = staffMapper.getListByCont(offSet,pageSize,buffer1.toString());
            if(count <=0){
                buffer2.append(" and staff_name like \'%"+keyword+"%\'");
                count = staffMapper.getCountByCont(buffer2.toString());
                list = staffMapper.getListByCont(offSet,pageSize,buffer2.toString());
            }
        }else{
            count = staffMapper.getCountByCont(cont.toString());
            list = staffMapper.getListByCont(offSet,pageSize,cont.toString());
        }
        Page<Staff> parkingPage = new Page<>(list,page,pageSize,count);
        return parkingPage;
    }

    @Override
    public List<Department> getDepartmentList() {
        List<Department> list = new ArrayList<>();
        list = departmentMapper.getAllList();
        return list;
    }

    @Override
    public Staff getOneByStaffId(Staff staff) {
        Staff staff1 = staffMapper.getOneByStaffId(staff.getStaffId());
        return staff1;
    }

    @Override
    public int saveOrUpdateOne(Staff staff) {
        Integer id = staff.getId();
        int num = 0;
        staff.setActivity(1);
        if(id == null){
            num = staffMapper.insertSelective(staff);
        }else{
            num = staffMapper.updateByPrimaryKeySelective(staff);
        }
        return num;
    }

    @Override
    public int deleteOneById(Integer id) {
        int num = 0;
        Staff staff = new Staff();
        staff.setActivity(0);
        staff.setId(id);
        num = staffMapper.updateByPrimaryKeySelective(staff);
        return num;
    }
}
