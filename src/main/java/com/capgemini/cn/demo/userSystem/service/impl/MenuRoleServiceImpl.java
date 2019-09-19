package com.capgemini.cn.demo.userSystem.service.impl;

import com.capgemini.cn.demo.userSystem.entity.Role;
import com.capgemini.cn.demo.userSystem.mapper.MenuRoleMapper;
import com.capgemini.cn.demo.userSystem.service.MenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/8 11:05
 */
@Service
public class MenuRoleServiceImpl implements MenuRoleService {

    @Autowired
    MenuRoleMapper menuRoleMapper;



    @Override
    public List<Role> getRolesByMenuId(Long menuId) {
        return menuRoleMapper.getRolesByMenuId(menuId);
    }

    @Override
    public Integer addMenuRole(Long menuRoleId, Long menuId, Long roleId) {
        return menuRoleMapper.addMenuRole(menuRoleId, menuId, roleId);
    }
}
