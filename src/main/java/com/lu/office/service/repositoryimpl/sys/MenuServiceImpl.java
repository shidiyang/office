package com.lu.office.service.repositoryimpl.sys;

import com.lu.office.model.sys.Menu;
import com.lu.office.model.sys.Page;
import com.lu.office.model.sys.Permission;
import com.lu.office.service.dao.sys.MenuMapper;
import com.lu.office.service.dao.sys.PermissionMapper;
import com.lu.office.service.repository.sys.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 5/4/17.
 */
@Repository("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Page<Menu> getPageList(int page, int pageSize, Integer parentId) {
        int offSet = (page-1)*pageSize;
        List<Menu> list = new ArrayList<>();
        int count = 0;
        if(parentId==null){
            count = menuMapper.getAllCount();
            list = menuMapper.getPage(offSet,pageSize);
        }else{
            count = menuMapper.getAllCountByParentId(parentId);
            list = menuMapper.getPageByParentId(offSet,pageSize,parentId);
        }

        Page<Menu> page1 = new Page<>(list,page,pageSize,count);

        return page1;
    }

    @Override
    public List<Menu> getMenus() {
        List<Menu> menus = menuMapper.getAll();
        return menus;
    }

    @Override
    public Menu getOneById(Menu menu) {
        StringBuffer cont = new StringBuffer(" and menu_id = "+menu.getMenuId());
        if(menu.getId()!=null){
            cont.append(" and id != "+menu.getId());
        }
        Menu menu1 = menuMapper.getOneByCont(cont.toString());
        return menu1;
    }

    @Override
    public Menu getOneByName(Menu menu) {
        StringBuffer cont = new StringBuffer(" and text = \'"+menu.getText()+"\'");
        if(menu.getId()!=null){
            cont.append(" and id != "+menu.getId());
        }
        Menu menu1 = menuMapper.getOneByCont(cont.toString());
        return menu1;
    }

    @Override
    public Permission getPermissionByName(Menu menu) {
        Permission permission = permissionMapper.getOneByName(menu.getPermissionName());
        return permission;
    }

    @Override
    public int saveOneMenu(Menu menu) {
        Permission permission = permissionMapper.getOneByName(menu.getPermissionName());
        if(permission == null) {
            Integer max = permissionMapper.getMaxId()+1;
            Permission permission1 = new Permission();
            permission1.setPermissionId(max);
            permission1.setDescription(menu.getPermissionName());
            permissionMapper.insertSelective(permission1);
            menu.setPermissionId(max);
        }else {
            menu.setPermissionId(permission.getPermissionId());
        }
        int num = 0;
        if(menu.getId() == null){
            num = menuMapper.insertSelective(menu);
        }else{
            num = menuMapper.updateByPrimaryKeySelective(menu);
        }
        return num;
    }

    @Override
    public int deleteOneById(Integer id) {
        int num = menuMapper.deleteByPrimaryKey(id);
        return num;
    }

}

