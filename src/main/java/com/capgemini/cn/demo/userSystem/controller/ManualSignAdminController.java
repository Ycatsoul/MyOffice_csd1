package com.capgemini.cn.demo.userSystem.controller;


import com.capgemini.cn.demo.aop.ControllerLog;
import com.capgemini.cn.demo.baseVo.DeleteVo;
import com.capgemini.cn.demo.baseVo.RespBean;
import com.capgemini.cn.demo.baseVo.RespVos;
import com.capgemini.cn.demo.userSystem.service.ManualSignService;
import com.capgemini.cn.demo.userSystem.vo.request.ManualSignEditVo;
import com.capgemini.cn.demo.userSystem.vo.request.ManualSignSearchVo;
import com.capgemini.cn.demo.userSystem.vo.response.ManualSignVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author WYQ
 * @description:一人做事一人当
 * @description:小丁做事小叮当
 * @date 2019/9/24 10:00
 */
@Api
@RestController
@RequestMapping("/manualSignAdmin")
public class ManualSignAdminController {
    @Autowired
    ManualSignService manualSignService;


    @ApiOperation("获取签到详尽信息")
    @GetMapping("/{manualSignId}")
    public RespBean getManualSign(@PathVariable Long manualSignId){
        RespVos<ManualSignVo> respVos = manualSignService.getManualSign(manualSignId);

        if (respVos != null && respVos.getSize() > 0) {
            return RespBean.ok("查询成功",respVos);
        }

        return RespBean.error("查询失败! 未找到相关信息!");
    }

    @ApiOperation("获取签到列表")
    @PostMapping("/list")
    public RespBean listManualSigns(@RequestBody ManualSignSearchVo manualSignSearchVo) {
        RespVos<ManualSignVo> respVos = manualSignService.listManualSigns(manualSignSearchVo);

        if (respVos != null && respVos.getSize() > 0) {
            return RespBean.ok("查询成功", respVos);
        }

        return RespBean.error("查询失败! 未找到相关信息!");
    }

    @ControllerLog(name = "修改签到信息")
    @ApiOperation("修改签到信息")
    @PutMapping("/")
    public RespBean updateManualSign(@RequestBody ManualSignEditVo manualSignEditVo) {
        Integer res = manualSignService.updateManualSign(manualSignEditVo);

        return res > 0 ? RespBean.ok("修改成功!") : RespBean.error("修改失败! 未找到相关信息!");
    }

    @ControllerLog(name = "删除签到信息")
    @ApiOperation("删除签到")
    @PostMapping("/delete")
    public RespBean deleteManualSigns(@RequestBody DeleteVo deleteVo) {
        Integer res = manualSignService.deleteManualSigns(deleteVo);

        return res > 0 ? RespBean.ok("删除成功!") : RespBean.error("删除失败! 未找到相关信息!");
    }
}
