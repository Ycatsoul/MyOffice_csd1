package com.capgemini.cn.demo.userSystem.service.impl;

import com.capgemini.cn.demo.baseVo.DeleteVo;
import com.capgemini.cn.demo.baseVo.RespVos;
import com.capgemini.cn.demo.userSystem.entity.Branch;
import com.capgemini.cn.demo.userSystem.mapper.BranchMapper;
import com.capgemini.cn.demo.userSystem.mapper.OperationLogMapper;
import com.capgemini.cn.demo.userSystem.service.BranchService;
import com.capgemini.cn.demo.userSystem.vo.request.BranchEditVo;
import com.capgemini.cn.demo.userSystem.vo.request.BranchSearchVo;
import com.capgemini.cn.demo.userSystem.vo.response.BranchVo;
import com.capgemini.cn.demo.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Classname :
 * @author: GuoBingjun
 * @date:
 */
@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    BranchMapper branchMapper;

    @Override
    public RespVos<BranchVo> getBranch(Long branchId) {
        Branch branch = branchMapper.getBranch(branchId);

        if (branch != null) {
            RespVos<BranchVo> respVos = new RespVos<>();
            respVos.setSize(1);
            respVos.setVos(new ArrayList<BranchVo>(){{
                add(convertToVo(branch));
            }});

            return respVos;
        }

        return null;
    }

    @Override
    public RespVos<BranchVo> listBranches(BranchSearchVo branchSearchVo) {
        List<Branch> branches = branchMapper.listBranches(branchSearchVo);

        if (branches != null && branches.size() > 0) {
            RespVos<BranchVo> respVos = new RespVos<>();
            respVos.setSize(branchMapper.countBranches(branchSearchVo));
            respVos.setVos(branches.stream().map(this::convertToVo).collect(Collectors.toList()));

            return respVos;
        }

        return null;
    }

    @Override
    public Integer addBranch(BranchEditVo branchEditVo) {
        branchEditVo.setBranchId(IdWorker.get().nextId());

        return branchMapper.insertBranch(branchEditVo);
    }

    @Override
    public Integer updateBranch(BranchEditVo branchEditVo) {

        return branchMapper.updateBranch(branchEditVo);
    }

    @Override
    public Integer deleteBranches(DeleteVo deleteVo) {

        return branchMapper.deleteBranches(deleteVo.getIds());
    }

    private BranchVo convertToVo(Branch branch) {
        BranchVo branchVo = new BranchVo();

        branchVo.setBranchId(branch.getBranchId());
        branchVo.setBranchName(branch.getBranchName());
        branchVo.setBranchShortName(branch.getBranchShortName());

        return branchVo;
    }

}
