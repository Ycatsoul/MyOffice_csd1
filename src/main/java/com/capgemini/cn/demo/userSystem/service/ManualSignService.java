package com.capgemini.cn.demo.userSystem.service;

import com.capgemini.cn.demo.baseVo.DeleteVo;
import com.capgemini.cn.demo.baseVo.RespVos;
import com.capgemini.cn.demo.userSystem.vo.request.ManualSignEditVo;
import com.capgemini.cn.demo.userSystem.vo.request.ManualSignSearchVo;
import com.capgemini.cn.demo.userSystem.vo.response.ManualSignVo;

/**
 * @author WYQ
 * @description:一人做事一人当
 * @description:小丁做事小叮当
 * @date 2019/9/23 15:20
 */

public interface ManualSignService {


        RespVos<ManualSignVo> getManualSign(Long manualSignId);

        RespVos<ManualSignVo> listManualSigns(ManualSignSearchVo manualSignSearchVo);

        ManualSignVo addManualSign(ManualSignEditVo manualSignEditVo);

        Integer updateManualSign(ManualSignEditVo manualSignEditVo);

        Integer deleteManualSigns(DeleteVo deleteVo);
    }
