package com.capgemini.cn.demo.userSystem.controller;

import com.capgemini.cn.core.commons.BaseController;
import com.capgemini.cn.demo.aop.ControllerLog;
import com.capgemini.cn.demo.baseVo.DeleteVo;
import com.capgemini.cn.demo.baseVo.RespBean;
import com.capgemini.cn.demo.baseVo.RespVos;
import com.capgemini.cn.demo.userSystem.service.UserService;
import com.capgemini.cn.demo.userSystem.vo.request.UserEditVo;
import com.capgemini.cn.demo.userSystem.vo.request.UserSearchVo;
import com.capgemini.cn.demo.userSystem.vo.response.BraDepUserVo;
import com.capgemini.cn.demo.userSystem.vo.response.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hasaker
 * @create_date 2019-08-19 22:32
 * @description
 */
@Slf4j
@Api
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @ApiOperation("查询用户")
    @GetMapping("/{userId}")
    public RespBean getUser(@PathVariable Long userId) {
        RespVos<UserVo> respVos = userService.getUser(userId);

        if (respVos != null && respVos.getSize() == 1) {
            return RespBean.ok("查询成功!", respVos);
        }

        return RespBean.error("查询失败!");
    }

    @ApiOperation("获取User列表")
    @PostMapping("/list")
    public RespBean listUsers(@RequestBody UserSearchVo userSearchVo){
        RespVos<UserVo> respVos = userService.listUsers(userSearchVo);

        if (respVos != null && respVos.getSize() > 0) {
            return RespBean.ok("查询成功!", respVos);
        }

        return RespBean.error("查询失败！");
    }

    @ApiOperation("获取机构-部门-用户树")
    @GetMapping("/braDepUserTree")
    public RespBean getBraDepUserTree() {
        RespVos<BraDepUserVo> respVos = userService.getBraDepUserTree();

        if (respVos != null && respVos.getSize() > 0) {
            return RespBean.ok("查询成功!", respVos);
        }

        return RespBean.error("查询失败！");
    }

    //@ControllerLog(name = "添加用户")
    @ApiOperation("添加User")
    @PostMapping("/")
    public RespBean addUser(@RequestBody UserEditVo userEditVo) {
        Integer res = userService.addUser(userEditVo);

        return res > 0 ? RespBean.ok("添加成功!") : RespBean.error("添加失败!");
    }

    @ControllerLog(name = "更新用户信息")
    @ApiOperation("更新User信息")
    @PutMapping("/")
    public RespBean updateUser(@RequestBody UserEditVo userEditVo) {
        Integer res = userService.updateUser(userEditVo);

        return res > 0 ? RespBean.ok("更新成功!") : RespBean.error("更新失败!");
    }

    @ControllerLog(name = "屏蔽用户")
    @ApiOperation("屏蔽User")
    @PutMapping("/block")
    public RespBean blockUsers(@RequestBody DeleteVo deleteVo) {
        Integer res = userService.blockUsers(deleteVo);

        return res > 0 ? RespBean.ok("成功屏蔽" + res + "个用户!") : RespBean.error("屏蔽失败!");
    }

    @ControllerLog(name = "删除用户")
    @ApiOperation("删除User")
    @PostMapping("/delete")
    public RespBean deleteUsers(@RequestBody DeleteVo deleteVo) {
        Integer res = userService.deleteUsers(deleteVo);

        return res > 0 ? RespBean.ok("成功删除" + res + "个用户!") : RespBean.error("删除失败!");
    }


}
