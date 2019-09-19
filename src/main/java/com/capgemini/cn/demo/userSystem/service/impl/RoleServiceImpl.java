package com.capgemini.cn.demo.userSystem.service.impl;

import com.capgemini.cn.demo.baseVo.DeleteVo;
import com.capgemini.cn.demo.userSystem.entity.Role;
import com.capgemini.cn.demo.userSystem.mapper.RoleMapper;
import com.capgemini.cn.demo.userSystem.service.RoleService;
import com.capgemini.cn.demo.userSystem.vo.request.RoleEditVo;
import com.capgemini.cn.demo.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/8 00:42
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public Role getRoleByRoleId(Long roleId) {
        return roleMapper.getRoleByRoleId(roleId);
    }

    @Override
    public Role getRoleByRoleName(String roleName) {
        return roleMapper.getRoleByRoleName(roleName);
    }

    @Override
    public List<Role> getRoles() {
        return roleMapper.getRoles();
    }

    @Override
    public Integer addRole(RoleEditVo roleEditVo) {
        roleEditVo.setRoleId(IdWorker.get().nextId());

        return roleMapper.addRole(roleEditVo);
    }

    @Override
    public Integer updateRole(RoleEditVo roleEditVo) {
        return roleMapper.updateRole(roleEditVo);
    }

    @Override
    public Integer deleteRole(DeleteVo deleteVo) {
        return roleMapper.deleteRole(deleteVo);
    }
}
