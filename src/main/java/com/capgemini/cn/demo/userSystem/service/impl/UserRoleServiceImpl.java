package com.capgemini.cn.demo.userSystem.service.impl;

import com.capgemini.cn.demo.userSystem.entity.Role;
import com.capgemini.cn.demo.userSystem.mapper.UserRoleMapper;
import com.capgemini.cn.demo.userSystem.service.UserRoleService;
import com.capgemini.cn.demo.userSystem.vo.request.UserRoleEditVo;
import com.capgemini.cn.demo.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/8 00:54
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public List<Role> getRolesByUserId(Long userId) {
        return userRoleMapper.getRolesByUserId(userId);
    }

    @Override
    public Integer addUserRole(UserRoleEditVo userRoleEditVo) {
        return userRoleMapper.addUserRole(
                    IdWorker.get().nextId(),
                    userRoleEditVo.getUserId(),
                    userRoleEditVo.getRoleId());
    }

    @Override
    public Integer deleteUserRoleByUserRoleId(Long userRoleId) {
        return userRoleMapper.deleteUserRoleByUserRoleId(userRoleId);
    }

    @Override
    public Integer deleteUserRoleByUserIdAndRoleId(Long userId, Long roleId) {
        return userRoleMapper.deleteUserRoleByUserIdAndRoleId(userId, roleId);
    }

    @Override
    public Integer deleteUserRolesByUserId(Long userId) {
        return userRoleMapper.deleteUserRolesByUserId(userId);
    }
}
