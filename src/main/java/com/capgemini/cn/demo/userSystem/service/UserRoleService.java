package com.capgemini.cn.demo.userSystem.service;


import com.capgemini.cn.demo.userSystem.entity.Role;
import com.capgemini.cn.demo.userSystem.vo.request.UserRoleEditVo;

import java.util.List;


public interface UserRoleService {

    List<Role> getRolesByUserId(Long userId);

    Integer addUserRole(UserRoleEditVo userRoleEditVo);

    Integer deleteUserRoleByUserRoleId(Long userRoleId);

    Integer deleteUserRoleByUserIdAndRoleId(Long userId, Long roleId);

    Integer deleteUserRolesByUserId(Long userId);
}
