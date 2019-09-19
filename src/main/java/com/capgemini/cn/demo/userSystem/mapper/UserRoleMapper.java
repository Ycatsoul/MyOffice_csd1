package com.capgemini.cn.demo.userSystem.mapper;

import com.capgemini.cn.demo.userSystem.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/8 00:47
 */
@Mapper
@Repository
public interface UserRoleMapper {

    List<Role> getRolesByUserId(@Param("userId") Long userId);

    Integer addUserRole(@Param("userRoleId") Long userRoleId, @Param("userId") Long userId, @Param("roleId") Long roleId);

    Integer deleteUserRoleByUserRoleId(@Param("userRoleId") Long userRoleId);

    Integer deleteUserRoleByUserIdAndRoleId(@Param("userId") Long userId, @Param("roleId") Long roleId);

    Integer deleteUserRolesByUserId(@Param("userId") Long userId);
}
