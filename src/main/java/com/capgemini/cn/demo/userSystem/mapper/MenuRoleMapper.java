package com.capgemini.cn.demo.userSystem.mapper;

import com.capgemini.cn.demo.userSystem.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/8 10:49
 */
@Mapper
@Repository
public interface MenuRoleMapper {

    List<Role> getRolesByMenuId(@Param("menuId") Long menuId);

    Integer addMenuRole(@Param("menuRoleId") Long menuRoleId,
                        @Param("menuId") Long menuId,
                        @Param("roleId") Long roleId);
}