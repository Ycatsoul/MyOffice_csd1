package com.capgemini.cn.demo.manualSign.controller;


import com.capgemini.cn.demo.aop.ControllerLog;
import com.capgemini.cn.demo.baseVo.RespBean;
import com.capgemini.cn.demo.manualSign.service.ManualSignService;
import com.capgemini.cn.demo.manualSign.vo.request.ManualSignEditVo;
import com.capgemini.cn.demo.manualSign.vo.response.ManualSignVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping("/manualSignUser")
public class ManualSignUserController {

    @Autowired
    ManualSignService manualSignService;


    @ControllerLog(name = "签到")
    @ApiOperation("添加一个签到")
    @PostMapping("/")
    public RespBean addManualSign(@RequestBody ManualSignEditVo manualSignEditVo) {
        ManualSignVo manualSignVo;
        manualSignVo = manualSignService.addManualSign(manualSignEditVo);

        return manualSignEditVo != null ? RespBean.ok("添加成功!", manualSignVo) : RespBean.error("添加失败! 未找到相关信息!");
    }
}