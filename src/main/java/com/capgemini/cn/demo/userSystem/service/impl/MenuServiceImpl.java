package com.capgemini.cn.demo.userSystem.service.impl;

import com.capgemini.cn.demo.userSystem.entity.Menu;
import com.capgemini.cn.demo.userSystem.entity.Role;
import com.capgemini.cn.demo.userSystem.mapper.MenuMapper;
import com.capgemini.cn.demo.userSystem.mapper.MenuRoleMapper;
import com.capgemini.cn.demo.userSystem.service.MenuService;
import com.capgemini.cn.demo.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hasaker
 * @since 2019/9/8 11:04
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;
    @Autowired
    MenuRoleMapper menuRoleMapper;


    @Override
    public Menu getMenuByMenuId(Long menuId) {
        return menuMapper.getMenuByMenuId(menuId);
    }

    @Override
    public Menu getMenuByMenuName(String menuName) {
        return menuMapper.getMenuByMenuName(menuName);
    }

    @Override
    public Integer addMenu(Menu menu) {
        return menuMapper.addMenu(menu);
    }

    @Override
    public Map<String, List<Menu>> getMenuTree() {
        Map<String, List<Menu>> menuTree = new HashMap<>();
        List<Menu> allMenus = menuMapper.getAllMenus();
        List<Role> userRoles = UserUtils.getCurrentUser().getRoles();

        for (Menu menu : allMenus) {
                        if (menu.getParentMenuId().equals(0L) && containRoles(userRoles,
                                menuRoleMapper.getRolesByMenuId(menu.getMenuId()))) {
                            List<Menu> children = new ArrayList<>();
                            for (Menu child : allMenus) {
                                if (child.getParentMenuId().equals(menu.getMenuId()) && containRoles(userRoles,
                            menuRoleMapper.getRolesByMenuId(child.getMenuId()))) {
                        children.add(child);
                    }
                }
                menuTree.put(menu.getMenuName(), children);
            }
        }

        return menuTree;
    }

    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

    private boolean containRoles(List<Role> userRoles, List<Role> menuRoles) {
        List<Long> userRoleIds = new ArrayList<>();
        List<Long> menuRoleIds = new ArrayList<>();
        for (Role role : userRoles) {
            userRoleIds.add(role.getRoleId());
        }
        for (Role role : menuRoles) {
            menuRoleIds.add(role.getRoleId());
        }

        for (Long menuRoleId : menuRoleIds) {
            if (userRoleIds.contains(menuRoleId)) {
                return true;
            }
        }

        return false;
    }
}
