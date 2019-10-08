package com.capgemini.cn.demo.userSystem.controller;

import com.capgemini.cn.core.commons.BaseController;
import com.capgemini.cn.demo.aop.ControllerLog;
import com.capgemini.cn.demo.baseVo.DeleteVo;
import com.capgemini.cn.demo.baseVo.RespBean;
import com.capgemini.cn.demo.baseVo.RespVos;
import com.capgemini.cn.demo.userSystem.service.BranchService;
import com.capgemini.cn.demo.userSystem.vo.request.BranchEditVo;
import com.capgemini.cn.demo.userSystem.vo.request.BranchSearchVo;
import com.capgemini.cn.demo.userSystem.vo.response.BranchVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
@Api
@RestController
@RequestMapping("/branch")
public class BranchController extends BaseController {

    @Autowired
    BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @ApiOperation("根据id查询机构信息")
    @GetMapping("/{branchId}")
    public RespBean getBranch(@PathVariable("branchId") Long branchId){
        RespVos<BranchVo> respVos = branchService.getBranch(branchId);

        if (respVos != null) {
            return RespBean.ok("成功!", respVos);
        }

        return RespBean.error("失败!");
    }

    @ApiOperation("显示Branch列表")
    @PostMapping("/list")
    public RespBean listBranches(@RequestBody BranchSearchVo branchSearchVo){
        RespVos<BranchVo> respVos = branchService.listBranches(branchSearchVo);

        if (respVos != null) {
            return RespBean.ok("查询成功!", respVos);
        }

        return RespBean.error("查询失败！");
    }

    @ControllerLog(name = "添加Branch")
    @ApiOperation("添加Branch信息")
    @PostMapping("/")
    public RespBean addBranch(@RequestBody BranchEditVo branchEditVo){

        return branchService.addBranch(branchEditVo) > 0 ? RespBean.ok("添加成功!") : RespBean.error("添加失败!");
    }

    @ControllerLog(name = "修改Branch")
    @ApiOperation("修改Branch信息")
    @PutMapping("/")
    public RespBean updateBranch(@RequestBody BranchEditVo branchEditVo){

        return branchService.updateBranch(branchEditVo) > 0 ? RespBean.ok("编辑成功!") : RespBean.error("编辑失败!");
    }

    @ControllerLog(name = "删除Branch")
    @ApiOperation("删除Branch信息")
    @PostMapping("/delete")
    public RespBean deleteBranches(@RequestBody DeleteVo deleteVo){

        return branchService.deleteBranches(deleteVo) > 0 ? RespBean.ok("删除成功!") : RespBean.error("删除失败!");
    }
}
