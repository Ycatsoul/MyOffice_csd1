package com.capgemini.cn.demo.userSystem.mapper;

import com.capgemini.cn.demo.manualSign.vo.request.ManualSignEditVo;
import com.capgemini.cn.demo.manualSign.vo.request.ManualSignSearchVo;
import com.capgemini.cn.demo.userSystem.entity.ManualSign;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ManualSignMapper {
    ManualSign getManualSign( Long manualSignId);

    List<ManualSign> listManualSigns(@Param("manualSignSearchVo") ManualSignSearchVo manualSignSearchVo);

    Integer countManualSigns(@Param("manualSignSearchVo") ManualSignSearchVo manualSignSearchVo);

    Integer insertManualSign(@Param("manualSignEditVo") ManualSignEditVo manualSignEditVo);

    Integer updateManualSign(@Param("manualSignEditVo") ManualSignEditVo manualSignEditVo);

    Integer deleteManualSigns(@Param("manualSignIds") List<Long> manualSignIds);
}
