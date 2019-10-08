package com.capgemini.cn.demo.userSystem.service;


import com.capgemini.cn.demo.baseVo.DeleteVo;
import com.capgemini.cn.demo.baseVo.RespVos;
import com.capgemini.cn.demo.userSystem.vo.request.DepartmentEditVo;
import com.capgemini.cn.demo.userSystem.vo.request.DepartmentSearchVo;
import com.capgemini.cn.demo.userSystem.vo.response.DepartmentVo;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
public interface DepartmentService {
    
    RespVos<DepartmentVo> getDepartment(Long departmentId);

    RespVos<DepartmentVo> listDepartments(DepartmentSearchVo departmentSearchVo);

    Integer addDepartment(DepartmentEditVo departmentEditVo);

    Integer updateDepartment(DepartmentEditVo departmentEditVo);

    Integer deleteDepartments(DeleteVo deleteVo);

}
