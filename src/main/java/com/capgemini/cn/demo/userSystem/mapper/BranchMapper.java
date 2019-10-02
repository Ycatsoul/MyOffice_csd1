package com.capgemini.cn.demo.userSystem.mapper;

import com.capgemini.cn.demo.userSystem.entity.Branch;
import com.capgemini.cn.demo.userSystem.vo.request.BranchEditVo;
import com.capgemini.cn.demo.userSystem.vo.request.BranchSearchVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:机构信息mapper
 * @Classname :BranchMapper
 * @author: Cola Guo
 * @date:2019-8-26 15:51
 */
@Repository
@Mapper
public interface BranchMapper {
    Branch getBranch(@Param("branchId") Long branchId);

    List<Branch> listBranches(@Param("branchSearchVo") BranchSearchVo branchSearchVo);

    Integer countBranches(@Param("branchSearchVo") BranchSearchVo branchSearchVo);

    Integer insertBranch(@Param("branchEditVo") BranchEditVo branchEditVo);

    Integer updateBranch(@Param("branchEditVo") BranchEditVo branchEditVo);

    Integer deleteBranches(@Param("branchIds") List<Long> branchIds);
}
