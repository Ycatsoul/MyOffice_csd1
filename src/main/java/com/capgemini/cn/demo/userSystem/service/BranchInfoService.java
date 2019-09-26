package com.capgemini.cn.demo.userSystem.service;

import com.capgemini.cn.demo.baseVo.RespVos;
import com.capgemini.cn.demo.userSystem.entity.BranchInfo;
import com.capgemini.cn.demo.userSystem.vo.response.BranchVo;
import com.capgemini.cn.demo.utils.IdToBeJson;
import java.util.List;

/**
 * wyq
 */
public interface BranchInfoService {

	List<BranchInfo>  selectAllBranchInfo();
	void insertBranchInfo(BranchInfo branchInfo);
	void updateBranchInfo(BranchInfo branchInfo);
	void deleteBranchInfoById(IdToBeJson branchId);

	Long selectBranchInfoIdByName(String name);

	RespVos<BranchVo> getBranch(Long branchId);


}
