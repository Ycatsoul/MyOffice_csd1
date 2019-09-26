package com.capgemini.cn.demo.userSystem.service;
import com.capgemini.cn.demo.baseVo.RespVos;
import com.capgemini.cn.demo.userSystem.entity.DepartInfo;
import com.capgemini.cn.demo.userSystem.vo.response.DepartmentVo;
import com.capgemini.cn.demo.utils.IdToBeJson;
import java.util.List;

/**
 * wyq
 */
public interface DepartService {

	List<DepartInfo> selectAllDepart();
	void insertDepart(DepartInfo departInfo);
	void updateDepart(DepartInfo departInfo);
	void deleteDepartById(IdToBeJson id);
	void deleteDepartBybranchId(IdToBeJson id);
	List<DepartInfo> selectAllDepartByBranchId(IdToBeJson id);

	RespVos<DepartmentVo> getDepartment(Long departmentId);
}
