package com.capgemini.cn.demo.userSystem.mapper;
import com.capgemini.cn.demo.userSystem.entity.DepartInfo;
import com.capgemini.cn.demo.utils.IdToBeJson;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DepartMapper {
	List<DepartInfo> selectAllDepart();
	void insertDepart(DepartInfo departInfo);
	void updateDepart(DepartInfo departInfo);
	void deleteDepartById(IdToBeJson id);
	void deleteDepartBybranchId(IdToBeJson id);
	List<DepartInfo> selectAllDepartByBranchId(IdToBeJson id);
	DepartInfo getDepartInfoById(IdToBeJson id);
}
