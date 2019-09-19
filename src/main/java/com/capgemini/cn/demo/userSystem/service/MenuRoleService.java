package com.capgemini.cn.demo.userSystem.service;


import com.capgemini.cn.demo.userSystem.entity.Role;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/8 11:02
 */
public interface MenuRoleService {

    List<Role> getRolesByMenuId(Long menuId);

    Integer addMenuRole(Long menuRoleId, Long menuId, Long roleId);
}
