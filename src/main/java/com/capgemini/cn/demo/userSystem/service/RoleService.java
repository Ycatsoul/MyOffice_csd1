package com.capgemini.cn.demo.userSystem.service;


import com.capgemini.cn.demo.baseVo.DeleteVo;
import com.capgemini.cn.demo.userSystem.entity.Role;
import com.capgemini.cn.demo.userSystem.vo.request.RoleEditVo;

import java.util.List;


public interface RoleService {

    Role getRoleByRoleId(Long roleId);

    Role getRoleByRoleName(String roleName);

    List<Role> getRoles();

    Integer addRole(RoleEditVo roleEditVo);

    Integer updateRole(RoleEditVo editVo);

    Integer deleteRole(DeleteVo deleteVo);
}
