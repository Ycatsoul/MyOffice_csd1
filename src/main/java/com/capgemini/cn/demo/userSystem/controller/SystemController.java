package com.capgemini.cn.demo.userSystem.controller;

import com.capgemini.cn.demo.baseVo.RespBean;
import com.capgemini.cn.demo.baseVo.RespVos;
import com.capgemini.cn.demo.userSystem.entity.User;
import com.capgemini.cn.demo.userSystem.service.MenuService;
import com.capgemini.cn.demo.userSystem.service.UserRoleService;
import com.capgemini.cn.demo.userSystem.service.UserService;
import com.capgemini.cn.demo.userSystem.vo.request.UserRoleEditVo;
import com.capgemini.cn.demo.userSystem.vo.response.UserVo;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    @Autowired
    UserService userService;


    /*@ApiOperation("获取目录树")
    @GetMapping("/menuTree")
    public RespBean getMenuTree() {
        return RespBean.ok(menuService.getMenuTree());
    }*/

    @ApiOperation("获取目录树")
    @GetMapping("/menuTree")
    public RespBean getMenuTree() {
        return RespBean.ok(menuService.getMenuTree2());
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

    @ApiOperation("当前登陆用户")
    @GetMapping("/loginUser")
    public RespBean getLoginUser(){
        HttpServletRequest request= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session=request.getSession();
        RespVos<UserVo> respVos=userService.getUser((Long)session.getAttribute("currentUserId"));
        return RespBean.ok(respVos);
    }
}
