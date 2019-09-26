package com.capgemini.cn.demo.userSystem.controller;



import com.capgemini.cn.demo.baseVo.RespBean;
import com.capgemini.cn.demo.userSystem.entity.BranchInfo;
import com.capgemini.cn.demo.userSystem.service.BranchInfoService;
import com.capgemini.cn.demo.userSystem.service.DepartService;
import com.capgemini.cn.demo.utils.IdToBeJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "机构管理")
@RestController
public class BranchInfoController {

	@Autowired
	BranchInfoService branchInfoService;
	@Autowired
	DepartService departService;
	
	@GetMapping("/findAllBranch")
	@ApiOperation(value = "查找所有机构")
	public RespBean findAllBranch() {
		List<BranchInfo> list = branchInfoService.selectAllBranchInfo();
		return RespBean.ok(list);
	}
	
	@PutMapping("/addBranch")
	@ApiOperation(value = "添加机构")
	public RespBean addBranch(@Valid @RequestBody BranchInfo branchInfo) {
		branchInfoService.insertBranchInfo(branchInfo);
		return RespBean.ok("添加成功");
	}
	
	@PutMapping("/modifyBranchById")
	@ApiOperation(value = "根据id修改机构")
	public RespBean modifyBranch(@Valid @RequestBody BranchInfo branchInfo) {
		branchInfoService.updateBranchInfo(branchInfo);
		return RespBean.ok("修改成功");
	}
	
	@DeleteMapping("/deleteBranchById")
	@ApiOperation(value = "根据id删除机构")
	public RespBean deleteBranchById(@Valid @RequestBody IdToBeJson id) {
		branchInfoService.deleteBranchInfoById(id);
		//删除所有所属部门
		departService.deleteDepartBybranchId(id);
		return RespBean.ok("删除成功");
	}
}
