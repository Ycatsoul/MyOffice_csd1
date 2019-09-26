package com.capgemini.cn.demo.userSystem.service.impl;

import com.capgemini.cn.demo.baseVo.RespVos;
import com.capgemini.cn.demo.userSystem.entity.DepartInfo;
import com.capgemini.cn.demo.userSystem.mapper.DepartMapper;
import com.capgemini.cn.demo.userSystem.service.DepartService;
import com.capgemini.cn.demo.userSystem.vo.response.DepartmentVo;
import com.capgemini.cn.demo.utils.IdToBeJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartInfoServiceImpl implements DepartService {

	@Autowired
	DepartMapper mapper;
	@Override
	public List<DepartInfo> selectAllDepart() {

		return mapper.selectAllDepart();
	}

	@Override
	public void insertDepart(DepartInfo departInfo) {
		mapper.insertDepart(departInfo);
	}

	@Override
	public void updateDepart(DepartInfo departInfo) {
		mapper.updateDepart(departInfo);
	}

	@Override
	public void deleteDepartById(IdToBeJson id) {
		mapper.deleteDepartById(id);
	}

	@Override
	public void deleteDepartBybranchId(IdToBeJson id) {
		mapper.deleteDepartBybranchId(id);
	}

	@Override
	public List<DepartInfo> selectAllDepartByBranchId(IdToBeJson id) {
		return mapper.selectAllDepartByBranchId(id);
	}

	@Override
	public RespVos<DepartmentVo> getDepartment(Long departmentId) {
		return null;
	}


}