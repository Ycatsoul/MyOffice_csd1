package com.capgemini.cn.demo.userSystem.service;


import com.capgemini.cn.demo.userSystem.entity.Role;
import com.capgemini.cn.demo.userSystem.vo.request.UserRoleEditVo;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/8 00:53
 */
public interface UserRoleService {

    List<Role> getRolesByUserId(Long userId);

    Integer addUserRole(UserRoleEditVo userRoleEditVo);

    Integer deleteUserRoleByUserRoleId(Long userRoleId);

    Integer deleteUserRoleByUserIdAndRoleId(Long userId, Long roleId);

    Integer deleteUserRolesByUserId(Long userId);
}
