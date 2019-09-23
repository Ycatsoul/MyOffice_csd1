package com.capgemini.cn.demo.userSystem.mapper;

import com.capgemini.cn.demo.userSystem.entity.BranchInfo;
import com.capgemini.cn.demo.utils.IdToBeJson;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BranchInfoMapper {
	List<BranchInfo>  selectAllBranchInfo();
	void insertBranchInfo(BranchInfo branchInfo);
	void updateBranchInfo(BranchInfo branchInfo);
	void deleteBranchInfoById(IdToBeJson branchId);
	
	long selectBranchInfoIdByName(String name);
}
