package com.capgemini.cn.demo.userSystem.controller;

import com.capgemini.cn.demo.baseVo.RespBean;
import com.capgemini.cn.demo.userSystem.service.MenuService;
import com.capgemini.cn.demo.userSystem.service.UserRoleService;
import com.capgemini.cn.demo.userSystem.vo.request.UserRoleEditVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hasaker
 * @since 2019/9/10 01:36
 */
@Api
@RestController
@RequestMapping("/system")
public class SystemController {

    @Autowired
    MenuService menuService;
    @Autowired
    UserRoleService userRoleService;


    @ApiOperation("获取目录树")
    @GetMapping("/menuTree")
    public RespBean getMenuTree() {

        return RespBean.ok(menuService.getMenuTree());
    }

    @ApiOperation("为用户添加权限")
    @PostMapping("/addRoleToUser")
    public RespBean addRoleToUser(UserRoleEditVo userRoleEditVo) {
        Integer res = userRoleService.addUserRole(userRoleEditVo);

        return res > 0 ? RespBean.ok("添加成功!") : RespBean.error("添加失败!");
    }

    @ApiOperation("删除用户权限")
    @DeleteMapping("/userRole/{userId}/{roleId}")
    public RespBean deleteRoleFromUser(@PathVariable Long userId, @PathVariable Long roleId) {
        Integer res = userRoleService.deleteUserRoleByUserIdAndRoleId(userId, roleId);

        return res > 0 ? RespBean.ok("删除成功!") : RespBean.error("删除失败!");
    }
}
