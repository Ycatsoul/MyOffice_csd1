package com.capgemini.cn.demo.userSystem.controller;

import com.capgemini.cn.demo.baseVo.RespBean;
import com.capgemini.cn.demo.userSystem.entity.DepartInfo;
import com.capgemini.cn.demo.userSystem.service.BranchInfoService;
import com.capgemini.cn.demo.userSystem.service.DepartService;
import com.capgemini.cn.demo.utils.IdToBeJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "部门管理")
public class DepartmentController {
	@Autowired
	DepartService departService;
	@Autowired
	BranchInfoService branchInfoService;

	@GetMapping("/findAllDepart")
	@ApiOperation(value = "查找所有部门")
	public RespBean findAllDepart() {
		List<DepartInfo> list = departService.selectAllDepart();
		return RespBean.ok(list);
	}

	@PutMapping("/addDepart")
	@ApiOperation(value = "添加部门")
	public RespBean addDepart(@Valid @RequestBody DepartInfo departInfo) {
		Long branchId;
		try {
			//根据机构名称查找机构Id
			branchId = branchInfoService.selectBranchInfoIdByName(departInfo.getBranchName());
		} catch (Exception e) {
			return RespBean.error("没有找到该部门");
		}
		//把查到的机构id给部门表里的机构id
		departInfo.setBranchId((long) branchId);

		departService.insertDepart(departInfo);
		return RespBean.ok("添加成功");
	}

	@PutMapping("/modifyDepartById")
	@ApiOperation(value = "根据id修改部门")
	public RespBean modifyDepartById(@Valid @RequestBody DepartInfo departInfo) {
		Long branchId;
		try {
			//根据机构名称查找机构Id
			branchId = branchInfoService.selectBranchInfoIdByName(departInfo.getBranchName());
		} catch (Exception e) {
			return RespBean.error("没有找到该部门");
		}
		//把查到的机构id给部门表里的机构id
		departInfo.setBranchId(branchId);

		departService.updateDepart(departInfo);
		return RespBean.ok("修改成功");
	}

	@DeleteMapping("/deleteDepartById")
	@ApiOperation(value = "根据id删除部门")
	public RespBean deleteDepartById(@Valid @RequestBody IdToBeJson id) {
		departService.deleteDepartById(id);
		return RespBean.ok("删除成功");
	}

	@PutMapping("/findAllDepartByBranchId")
	@ApiOperation(value = "查找机构下的所有部门")
	public RespBean findAllDepartByBranceId(@Valid @RequestBody IdToBeJson id) {
		System.out.println("id+++++++++++++++++++++：：：：：" + id);
		List<DepartInfo> list = departService.selectAllDepartByBranchId(id);
		return RespBean.ok(list);
	}
}
