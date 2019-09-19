package com.capgemini.cn.demo.userSystem.controller;

import com.capgemini.cn.demo.aop.ControllerLog;
import com.capgemini.cn.demo.baseVo.DeleteVo;
import com.capgemini.cn.demo.baseVo.RespBean;
import com.capgemini.cn.demo.baseVo.RespVos;
import com.capgemini.cn.demo.userSystem.service.OperationLogService;
import com.capgemini.cn.demo.userSystem.vo.request.OperationLogSearchVo;
import com.capgemini.cn.demo.userSystem.vo.response.OperationLogVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hasaker
 * @since 2019/9/4 22:44
 */
@Api
@RestController
@RequestMapping("/operationLog")
public class OperationLogController {

    @Autowired
    OperationLogService operationLogService;


    @ApiOperation("列出OperationLog")
    @PostMapping("/")
    public RespBean listOperationLogs(@RequestBody OperationLogSearchVo operationLogSearchVo) {
        RespVos<OperationLogVo> respVos = operationLogService.listOperationLogs(operationLogSearchVo);

        if (respVos.getSize() > 0) {
            return RespBean.ok(respVos);
        }

        return RespBean.error("没有查询到相关信息!");
    }

    @ControllerLog(name = "删除一条操作记录")
    @ApiOperation("删除OperationLog")
    @PostMapping("/delete")
    public RespBean deleteOperationLogs(@RequestBody DeleteVo deleteVo) {
        Integer res = operationLogService.deleteOperationLogs(deleteVo);

        return res > 0 ? RespBean.ok("成功删除" + res + "条操作日志!") : RespBean.error("删除失败!");
    }
}
