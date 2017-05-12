package com.lu.office.service.repository.sys;

import com.lu.office.model.sys.Menu;
import com.lu.office.model.sys.Page;
import com.lu.office.model.sys.Permission;

import java.util.List;

/**
 * Created by user on 5/4/17.
 */
public interface MenuService {

    Page<Menu> getPageList(int page, int pageSize, Integer parentId);

    List<Menu> getMenus();

    Menu getOneById(Menu menu);

    Menu getOneByName(Menu menu);

    Permission getPermissionByName(Menu menu);

    int saveOneMenu(Menu menu);

    int deleteOneById(Integer id);

}
