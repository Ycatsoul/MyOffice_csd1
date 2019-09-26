package com.capgemini.cn.demo.userSystem.mapper;
import com.capgemini.cn.demo.userSystem.entity.DepartInfo;
import com.capgemini.cn.demo.utils.IdToBeJson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DepartMapper {

	/*查找部门*/
	List<DepartInfo> selectAllDepart();

	/*添加部门*/
	void insertDepart(DepartInfo departInfo);

	/*修改部门*/
	void updateDepart(DepartInfo departInfo);

	/*按部门ID删除部门*/
	void deleteDepartById(IdToBeJson id);

	/*按机构ID删除部门*/
	void deleteDepartBybranchId(IdToBeJson id);

	/*按照机构ID查找部门*/
	List<DepartInfo> selectAllDepartByBranchId(IdToBeJson id);


	DepartInfo getDepartment(@Param("departmentId") Long departmentId);

}
