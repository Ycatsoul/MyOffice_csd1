package com.capgemini.cn.demo.manualSign.service.impl;

import com.capgemini.cn.demo.baseVo.DeleteVo;
import com.capgemini.cn.demo.baseVo.RespVos;
import com.capgemini.cn.demo.manualSign.entity.ManualSign;
import com.capgemini.cn.demo.manualSign.mapper.ManualSignMapper;
import com.capgemini.cn.demo.manualSign.service.ManualSignService;
import com.capgemini.cn.demo.manualSign.vo.request.ManualSignEditVo;
import com.capgemini.cn.demo.manualSign.vo.request.ManualSignSearchVo;
import com.capgemini.cn.demo.manualSign.vo.response.ManualSignVo;
import com.capgemini.cn.demo.userSystem.entity.BranchInfo;
import com.capgemini.cn.demo.userSystem.entity.DepartInfo;
import com.capgemini.cn.demo.userSystem.entity.User;
import com.capgemini.cn.demo.userSystem.mapper.BranchInfoMapper;
import com.capgemini.cn.demo.userSystem.mapper.DepartMapper;
import com.capgemini.cn.demo.userSystem.mapper.UserMapper;
import com.capgemini.cn.demo.utils.IdToBeJson;
import com.capgemini.cn.demo.utils.IdWorker;
import com.capgemini.cn.demo.utils.UserUtils;
import io.micrometer.core.instrument.Meter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author WYQ
 * @description:一人做事一人当
 * @description:小丁做事小叮当
 * @date 2019/9/23 16:08
 */
@Service
public class ManualSignServiceImpl implements ManualSignService {

    @Autowired
    BranchInfoMapper branchInfoMapper;

    @Autowired
    ManualSignMapper manualSignMapper;

    @Autowired
    DepartMapper departMapper;

    @Autowired
    UserMapper userMapper;



    @Override
    public RespVos<ManualSignVo> getManualSign(Long manualSignId) {
        return null;
    }

    @Override
    public RespVos<ManualSignVo> listManualSigns(ManualSignSearchVo manualSignSearchVo) {
        List<ManualSign> manualSigns = manualSignMapper.listManualSigns(manualSignSearchVo);

        if (manualSigns != null && manualSigns.size() > 0) {
            RespVos<ManualSignVo> respVos = new RespVos<>();
            respVos.setSize(manualSignMapper.countManualSigns(manualSignSearchVo));
            respVos.setVos(manualSigns.stream().map(this::convertToVo).collect(Collectors.toList()));

            return respVos;
        }

        return null;
    }

/*添加签到信息*/
@Override
public ManualSignVo addManualSign(ManualSignEditVo manualSignEditVo) {
    manualSignEditVo.setManualSignId(IdWorker.get().nextId());
    manualSignEditVo.setUserId(UserUtils.getCurrentUser().getUserId());
    manualSignEditVo.setSignTime(new Date());
    Integer res = manualSignMapper.insertManualSign(manualSignEditVo);

    ManualSign manualSign = manualSignMapper.getManualSign(manualSignEditVo.getManualSignId());
    User user = userMapper.getUser(manualSignEditVo.getUserId());
    IdToBeJson idToBeJson=new IdToBeJson();
    idToBeJson.setId(user.getDepartmentId());
    DepartInfo departInfo = departMapper.getDepartInfoById(idToBeJson);
    BranchInfo branchInfo = branchInfoMapper.getBranchInfoById(departInfo.getBranchId());

    ManualSignVo manualSignVo = new ManualSignVo();
    manualSignVo.setManualSignId(manualSign.getManualSignId());
    manualSignVo.setUserId(user.getUserId());
    manualSignVo.setUsername(user.getUsername());
    manualSignVo.setName(user.getName());
    manualSignVo.setDepartmentName(departInfo.getDepartName());
    manualSignVo.setBranchName(branchInfo.getBranchName());
    manualSignVo.setSignDesc(manualSign.getSignDesc());
    manualSignVo.setSignTag(manualSign.getSignTag());
    manualSignVo.setSignTime(manualSign.getSignTime());

    return res > 0 ? manualSignVo : null;
}

    @Override
    public Integer updateManualSign(ManualSignEditVo manualSignEditVo) {

        return manualSignMapper.updateManualSign(manualSignEditVo);
    }

    @Override
    public Integer deleteManualSigns(DeleteVo deleteVo) {

        return manualSignMapper.deleteManualSigns(deleteVo.getIds());
    }

    private ManualSignVo convertToVo(ManualSign manualSign) {

        ManualSignVo manualSignVo = new ManualSignVo();
        User user = userMapper.getUser(manualSign.getUserId());

        IdToBeJson idToBeJson=new IdToBeJson();
        idToBeJson.setId(user.getDepartmentId());
        DepartInfo departInfo = departMapper.getDepartInfoById(idToBeJson);
        BranchInfo branchInfo = branchInfoMapper.getBranchInfoById(departInfo.getBranchId());

        manualSignVo.setManualSignId(manualSign.getManualSignId());
        manualSignVo.setUserId(manualSign.getUserId());
        manualSignVo.setUsername(user.getUsername());
        manualSignVo.setName(user.getName());
        manualSignVo.setDepartmentName(departInfo.getDepartName());
        manualSignVo.setBranchName(branchInfo.getBranchName());
        manualSignVo.setSignTime(manualSign.getSignTime());
        manualSignVo.setSignDesc(manualSign.getSignDesc());
        manualSignVo.setSignTag(manualSign.getSignTag());
        return manualSignVo;
    }
}
