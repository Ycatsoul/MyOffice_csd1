package com.capgemini.cn.demo.userSystem.mapper;

import com.capgemini.cn.demo.baseVo.DeleteVo;
import com.capgemini.cn.demo.userSystem.entity.Role;
import com.capgemini.cn.demo.userSystem.vo.request.RoleEditVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/8/29 14:13
 */
@Mapper
@Repository
public interface RoleMapper {

    Role getRoleByRoleId(@Param("roleId") Long roleId);

    Role getRoleByRoleName(@Param("roleName") String roleName);

    List<Role> getRoles();

    Integer addRole(@Param("roleEditVo") RoleEditVo roleEditVo);

    Integer updateRole(@Param("roleEditVo") RoleEditVo roleEditVo);

    Integer deleteRole(@Param("deleteVo") DeleteVo deleteVo);
}
