package com.capgemini.cn.demo.userSystem.service.impl;

import com.capgemini.cn.demo.userSystem.entity.BranchInfo;
import com.capgemini.cn.demo.userSystem.mapper.BranchInfoMapper;
import com.capgemini.cn.demo.userSystem.service.BranchInfoService;
import com.capgemini.cn.demo.utils.IdToBeJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchInfoServiceImpl implements BranchInfoService {
	
	@Autowired
	BranchInfoMapper mapper;
	
	@Override
	public List<BranchInfo> selectAllBranchInfo() {
		return mapper.selectAllBranchInfo();
	}

	@Override
	public void insertBranchInfo(BranchInfo branchInfo) {
		mapper.insertBranchInfo(branchInfo);
	}

	@Override
	public void updateBranchInfo(BranchInfo branchInfo) {
		mapper.updateBranchInfo(branchInfo);
	}

	@Override
	public void deleteBranchInfoById(IdToBeJson branchId) {
		
		mapper.deleteBranchInfoById(branchId);
	}

	@Override
	public int selectBranchInfoIdByName(String name) {
		return mapper.selectBranchInfoIdByName(name);
	}

}
