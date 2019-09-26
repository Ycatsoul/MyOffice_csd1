package com.capgemini.cn.demo.userSystem.mapper;

import com.capgemini.cn.demo.userSystem.entity.ManualSign;
import com.capgemini.cn.demo.userSystem.vo.request.ManualSignEditVo;
import com.capgemini.cn.demo.userSystem.vo.request.ManualSignSearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ManualSignMapper {
    ManualSign getManualSign(@Param("manualSignId") Long manualSignId);

    List<ManualSign> listManualSigns(@Param("manualSignSearchVo") ManualSignSearchVo manualSignSearchVo);

    Integer countManualSigns(@Param("manualSignSearchVo") ManualSignSearchVo manualSignSearchVo);

    Integer insertManualSign(@Param("manualSignEditVo") ManualSignEditVo manualSignEditVo);

    Integer updateManualSign(@Param("manualSignEditVo") ManualSignEditVo manualSignEditVo);

    Integer deleteManualSigns(@Param("manualSignIds") List<Long> manualSignIds);
}
