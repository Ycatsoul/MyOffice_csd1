package com.capgemini.cn.demo.userSystem.controller;

import com.capgemini.cn.demo.baseVo.RespBean;
import com.capgemini.cn.demo.userSystem.service.UserService;
import com.capgemini.cn.demo.userSystem.vo.request.UserEditVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hasaker
 * @since 2019/9/9 10:07
 */
@Api
@RestController
public class LoginRegController {

    @Autowired
    UserService userService;


    @ApiOperation("跳转登录页")
    @GetMapping("/login_page")
    public RespBean login() {
        return RespBean.error("尚未登录, 请先登录!");
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public RespBean register(@RequestBody UserEditVo userEditVo) {
        Integer res = userService.addUser(userEditVo);

        if (res == -1) {
            return RespBean.error("用户名已存在!");
        }

        return res == 1 ? RespBean.ok("注册成功!") : RespBean.error("注册失败!");
    }
}
