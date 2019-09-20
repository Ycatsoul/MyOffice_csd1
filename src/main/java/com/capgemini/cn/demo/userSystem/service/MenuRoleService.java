package com.capgemini.cn.demo.userSystem.service;


import com.capgemini.cn.demo.userSystem.entity.Role;

import java.util.List;


public interface MenuRoleService {

    List<Role> getRolesByMenuId(Long menuId);

    Integer addMenuRole(Long menuRoleId, Long menuId, Long roleId);
}
