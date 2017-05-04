package com.lu.office.service.regositoryimlp.sys;

import com.lu.office.model.sys.Menu;
import com.lu.office.service.dao.sys.MenuMapper;
import com.lu.office.service.repository.sys.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on 5/4/17.
 */
@Repository("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getMenus() {
        List<Menu> menus = menuMapper.getAll();
        return menus;
    }
}

